package gh.gov.moh.admissionsportal.web.controller;

import gh.gov.moh.admissionsportal.dao.CertificateDao;
import gh.gov.moh.admissionsportal.model.*;
import gh.gov.moh.admissionsportal.service.*;
import gh.gov.moh.admissionsportal.web.Grade;
import gh.gov.moh.admissionsportal.web.Region;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


/**
 * Created by molayodecker on 28/01/2017.
 */
@Controller
public class ExamsController {

    @Autowired
    private Environment env;

    @Autowired
    private ExamService examService;
    @Autowired
    private SchoolService schoolService;
    @Autowired
    private UserService userService;
    @Autowired
    private CertificateService certificateService;
    @Autowired
    private CertificateDao certificateDao;
    @Autowired
    private PictureService pictureService;
    @Autowired
    private LoggerService loggerService;

    List<CertificateProgramme> certList = new ArrayList<>();

    /*This method overwrites the dateFormat. Changing the data format to dd-MM-yyyy was giving an error so i had to
    * over the date format*/
    @InitBinder
    public void allowEmptyDateBinding( WebDataBinder binder )
    {
        // Custom String Editor. tell spring to set empty values as null instead of empty string.
        binder.registerCustomEditor( String.class, new StringTrimmerEditor( true ));

        //Custom Date Editor

        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd/mm/yyyy");
        simpleDateFormat.setLenient(false);
        binder.registerCustomEditor( Date.class, new CustomDateEditor( simpleDateFormat,true));
    }

    private BufferedImage cropImageSquare(byte[] image) {
        // Get a BufferedImage object from a byte array
        BufferedImage croppedImage = null;
        try {
            InputStream in = new ByteArrayInputStream(image);
            BufferedImage originalImage = ImageIO.read(in);

            // Get image dimensions
            int height = originalImage.getHeight();
            int width = originalImage.getWidth();

            // The image is already a square
            if (height == width) {
                return originalImage;
            }

            // Compute the size of the square
            int squareSize = (height > width ? width : height);

            // Coordinates of the image's middle
            int xc = width / 2;
            int yc = height / 2;

            // Crop
            croppedImage = originalImage.getSubimage(
                    xc - (squareSize / 2), // x coordinate of the upper-left corner
                    yc - (squareSize / 2), // y coordinate of the upper-left corner
                    squareSize,            // widht
                    squareSize             // height
            );
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return croppedImage;
    }

    @RequestMapping(value = "/cert_prog", method = RequestMethod.GET)
    public String examsList(Model model, CertificateProgramme certificateProgramme, HttpServletRequest request, Principal principal, UrlLogger urlLogger) {
        User user = (User) ((UsernamePasswordAuthenticationToken) principal).getPrincipal();
        Iterable<Exams> exams = examService.findAll();
        Iterable<School> schools = schoolService.findAll();
        Iterable<CertificateProgramme> certificateProgrammes = certificateService.findAll();
        CertificateProgramme retrieveData = certificateService.ListAll();
        model.addAttribute("retrieveData", retrieveData);
        Iterable<Picture> pictures = pictureService.findAll();
        CertificateProgramme mycert = certificateService.flagger(certificateProgramme);
        model.addAttribute("mycert", mycert);
        model.addAttribute("exams", exams);
        model.addAttribute("pictures", pictures);
        String req = request.getRequestURI();
        model.addAttribute("req", req);
        //loggerService.logger(req);

        if (loggerService.findAll().size() == 0)
            loggerService.loggerURL(req, user.getId());
        else
            loggerService.logger(req);

        if (!model.containsAttribute("newExam")) {
            model.addAttribute("newExam", new Exams());
        }

        model.addAttribute("certificateProgrammes", certificateProgrammes);
        if (!model.containsAttribute("certificate")) {
            model.addAttribute("certificate", new CertificateProgramme());
        }
        model.addAttribute("grades", Grade.values());
        model.addAttribute("regions", Region.values());
        model.addAttribute("schools", schools);
        if (!model.containsAttribute("newSchool")) {
            model.addAttribute("newSchool", new School());
        }
        if(!model.containsAttribute("pictures")){
            model.addAttribute("picture", new Picture());
        }
        //Customizing the form submit
        model.addAttribute("action1", "/basic");
        model.addAttribute("action2", "/exams");
        model.addAttribute("action3", "/school");
        model.addAttribute("heading", "New Personal Information");
        model.addAttribute("weacheading", "New WAEC Information");
        model.addAttribute("schoolheading", "New selection of Health Training Institution");
        model.addAttribute("page_title", "WASSCE Applicant");
        model.addAttribute("submit", "Add");
        return "cert_prog";
    }

    @RequestMapping(path = "/mark", method = RequestMethod.POST)
    public String toggleComplete(@RequestParam Long id) {
        Exams exams = examService.findOne(id);
        examService.toggleComplete(id);
        return "redirect:/cert_prog";
    }

    @RequestMapping(value = "/exams", method = RequestMethod.POST)
    public String addExams(@Valid @ModelAttribute Exams exams, BindingResult result, RedirectAttributes redirectAttributes, Principal principal) {
        //User user = userService.findByUsername(principal.getName());
        User user = (User) ((UsernamePasswordAuthenticationToken) principal).getPrincipal();
        exams.setUser(user);
        //examService.increment(exams);
        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.newExam", result);
            //
            redirectAttributes.addFlashAttribute("newExam", exams);
            //redirect back to form
            return "redirect:/cert_prog";
        }
        examService.save(exams);

        //When data passes validation and saved, use flash to indicate it
        redirectAttributes.addFlashAttribute("flash", new FlashMessage("Form Sucessfully Submitted", FlashMessage.Status.SUCCESS));

        return "redirect:/cert_prog";
    }


    @RequestMapping(value = "/school", method = RequestMethod.POST)
    public String addSchool(@Valid @ModelAttribute School school, BindingResult result, Principal principal, RedirectAttributes redirectAttributes) {
        User user = (User) ((UsernamePasswordAuthenticationToken) principal).getPrincipal();
        school.setUser(user);

        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.newSchool", result);

            redirectAttributes.addFlashAttribute("newSchool", school);

            //redirect back to page
            return "redirect:/cert_prog";
        }

        schoolService.save(school);
        //When data passes validation and saved, use flash to indicate it
        redirectAttributes.addFlashAttribute("flash", new FlashMessage("Form Sucessfully Submitted", FlashMessage.Status.SUCCESS));
        return "redirect:/cert_prog";
    }

    @RequestMapping(value = "/basic", method = RequestMethod.POST)
    public String addCertificateForm(@Valid @ModelAttribute CertificateProgramme certificate, BindingResult result, RedirectAttributes redirectAttributes, Principal principal) {
        User user = (User) ((UsernamePasswordAuthenticationToken) principal).getPrincipal();
        certificate.setUser(user);

        if (result.hasErrors()) {
            //Include error validation upon redirect
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.certificate", result);

            //Add data if invalid data is received
            redirectAttributes.addFlashAttribute("certificate", certificate);

            //redirect back to page
            return "redirect:/cert_prog";
        }
        certificateService.flagger(certificate);
        //userService.setReg(users,req);
        certificateService.save(certificate);

        //When data passes validation and saved, use flash to indicate it
        redirectAttributes.addFlashAttribute("flash", new FlashMessage("Form Sucessfully Submitted", FlashMessage.Status.SUCCESS));
        return "redirect:/cert_prog";
    }

    //@RequestMapping("/img/{pictureId}.gif")
    /*@RequestMapping(value="/img/{pictureId}.gif")
    @ResponseBody
    public byte[] pictureImage(@PathVariable Long pictureId){
        return pictureService.findById(pictureId).getBytes();
    }*/

    @PostMapping(value = "/uploadFile")
    @ResponseBody
    public ResponseEntity<?> uploadFile(@RequestParam("uploadfile") MultipartFile uploadfile, Principal principal) {
        User user = (User) ((UsernamePasswordAuthenticationToken) principal).getPrincipal();
        List<Picture> pictureIterator = pictureService.findAll();
        Picture picture = new Picture();
        String filename;
        try {
            // Crop the image (uploadfile is an object of type MultipartFile)
            BufferedImage croppedImage = cropImageSquare(uploadfile.getBytes());
            // Get the filename and build the local file path
            filename = uploadfile.getOriginalFilename();
            String directory = env.getProperty("netgloo.paths.uploadedFiles");
            String filepath = Paths.get(directory, filename).toString();
            String ext = FilenameUtils.getExtension(filename);
            // Save the file locally
            File outPutFile = new File(filepath);
            ImageIO.write(croppedImage, ext, outPutFile);
            //stream.write(uploadfile.getBytes());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        System.out.println("File Length:" + filename.getBytes().length);
        System.out.println("File Name:" + filename.toString());
        picture.setUploadfile(filename);
        picture.setUser(user);
        pictureService.save(picture, uploadfile);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    //Form for editing personal information
    @RequestMapping("cert_prog/{certificateId}/edit")
    public String editCertificateForm(@PathVariable Long certificateId, Model model, CertificateProgramme certificateProgramme) {
        Iterable<Exams> exams = examService.findAll();
        Iterable<School> schools = schoolService.findAll();
        Iterable<CertificateProgramme> certificateProgrammes = certificateService.findAll();
        CertificateProgramme retrieveData = certificateService.ListAll();
        model.addAttribute("retrieveData", retrieveData);
        Iterable<Picture> pictures = pictureService.findAll();
        CertificateProgramme mycert = certificateService.flagger(certificateProgramme);
        model.addAttribute("mycert", mycert);
        model.addAttribute("exams", exams);
        model.addAttribute("grades", Grade.values());
        model.addAttribute("regions", Region.values());
        model.addAttribute("schools", schools);
        model.addAttribute("grades", Grade.values());
        if (!model.containsAttribute("certificate")) {
            model.addAttribute("certificate", certificateService.findOne(certificateId));
        }
        if (!model.containsAttribute("newExam")) {
            model.addAttribute("newExam", new Exams());
        }
        if (!model.containsAttribute("newSchool")) {
            model.addAttribute("newSchool", new School());
        }
        if(!model.containsAttribute("pictures")){
            model.addAttribute("picture", new Picture());
        }
        //model.addAttribute("action1", String.format("/basic/%s", certificateId));
        model.addAttribute("action1", "/basicAjax.json");
        model.addAttribute("heading", "Edit Personal Information");
        model.addAttribute("submit", "Update");
        return "edit_basic";
    }

    /*Form for editing exam information
     *
     */
    @RequestMapping("cert_prog/{examId}/editexams")
    public String editExamForm(@PathVariable Long examId, Model model, CertificateProgramme certificateProgramme) {
        Iterable<Exams> exams = examService.findAll();
        Iterable<School> schools = schoolService.findAll();
        Iterable<CertificateProgramme> certificateProgrammes = certificateService.findAll();
        CertificateProgramme retrieveData = certificateService.ListAll();
        model.addAttribute("retrieveData", retrieveData);
        Iterable<Picture> pictures = pictureService.findAll();
        CertificateProgramme mycert = certificateService.flagger(certificateProgramme);
        model.addAttribute("mycert", mycert);
        model.addAttribute("exams", exams);
        model.addAttribute("grades", Grade.values());
        model.addAttribute("regions", Region.values());
        model.addAttribute("schools", schools);
        model.addAttribute("grades", Grade.values());
        model.addAttribute("pictures", pictures);
        if (!model.containsAttribute("newExam")) {
            model.addAttribute("newExam", examService.findOne(examId));
        }
        if (!model.containsAttribute("certificate")) {
            model.addAttribute("certificate", new CertificateProgramme());
        }
        if (!model.containsAttribute("newSchool")) {
            model.addAttribute("newSchool", new School());
        }
        if(!model.containsAttribute("pictures")){
            model.addAttribute("picture", new Picture());
        }
        model.addAttribute("action2", String.format("/exams/%s", examId));
        model.addAttribute("weacheading", "Edit WAEC Information");
        model.addAttribute("submit", "Update");
        return "cert_prog";
    }

    @RequestMapping("cert_prog/{schoolId}/editschool")
    public String editSchoolForm(@PathVariable Long schoolId, Model model, CertificateProgramme certificateProgramme) {
        Iterable<Exams> exams = examService.findAll();
        Iterable<School> schools = schoolService.findAll();
        Iterable<CertificateProgramme> certificateProgrammes = certificateService.findAll();
        CertificateProgramme retrieveData = certificateService.ListAll();
        model.addAttribute("retrieveData", retrieveData);
        Iterable<Picture> pictures = pictureService.findAll();
        CertificateProgramme mycert = certificateService.flagger(certificateProgramme);
        model.addAttribute("mycert", mycert);
        model.addAttribute("exams", exams);
        model.addAttribute("grades", Grade.values());
        model.addAttribute("regions", Region.values());
        model.addAttribute("schools", schools);
        model.addAttribute("grades", Grade.values());
        model.addAttribute("pictures", pictures);
        if (!model.containsAttribute("newExam")) {
            model.addAttribute("newExam", new Exams());
        }
        if (!model.containsAttribute("certificate")) {
            model.addAttribute("certificate", new CertificateProgramme());
        }
        if (!model.containsAttribute("newSchool")) {
            model.addAttribute("newSchool", schoolService.findOne(schoolId));
        }
        if(!model.containsAttribute("pictures")){
            model.addAttribute("picture", new Picture());
        }
        model.addAttribute("action3", String.format("/school/%s", schoolId));
        model.addAttribute("schoolheading", "Edit selection of Health Training Institution");
        model.addAttribute("submit", "Update");
        return "cert_prog";
    }
    /*@{pictureId}
    * This method is supposed to get the data from the database so that
    * the user can update the picture and save it
    * */

    @GetMapping(value = "cert/{pictureId}/edit")
    public String editPicture(@PathVariable("pictureId") Long id, Model model, Picture picture){
        Iterable<Picture> pictures = pictureService.findAll();
        model.addAttribute(pictures);
        if(!model.containsAttribute("pictures")){
            model.addAttribute("picture", new Picture());
        }
        model.addAttribute("pictures", pictures);
        model.addAttribute("submit", "Update");
        return "cert_prog";
    }

    /*This method is used to update the certifcate form*/
    @RequestMapping(value = "/basic/{certificateId}", method = RequestMethod.POST)
    public String UpdateCertificateForm(@Valid @ModelAttribute CertificateProgramme certificate, BindingResult result, RedirectAttributes redirectAttributes, Principal principal) {
        User user = (User) ((UsernamePasswordAuthenticationToken) principal).getPrincipal();


        if (result.hasErrors()) {
            //Include error validation upon redirect
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.certificate", result);

            //Add data if invalid data is received
            redirectAttributes.addFlashAttribute("certificate", certificate);

            //redirect back to page
            return String.format("redirect:/cert_prog/%s/edit", certificate.getId());
        }
        certificateService.flagger(certificate);
        certificateService.save(certificate);

        //When data passes validation and saved, use flash to indicate it
        redirectAttributes.addFlashAttribute("flash", new FlashMessage("Personal Information Form have been Sucessfully Updated", FlashMessage.Status.SUCCESS));
        return "redirect:/cert_prog";
    }

    @RequestMapping(value = "/exams/{examsId}", method = RequestMethod.POST)
    public String UpdateExams(@Valid @ModelAttribute Exams exams, BindingResult result, RedirectAttributes redirectAttributes, Principal principal) {
        //User user = userService.findByUsername(principal.getName());
        User user = (User) ((UsernamePasswordAuthenticationToken) principal).getPrincipal();
        exams.setUser(user);
        //examService.increment(exams);
        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.newExam", result);
            //
            redirectAttributes.addFlashAttribute("newExam", exams);
            //redirect back to form
            return String.format("redirect:/cert_prog/%s/edit", exams.getId());
        }
        examService.save(exams);

        //When data passes validation and saved, use flash to indicate it
        redirectAttributes.addFlashAttribute("flash", new FlashMessage("WAEC Information Form have been Sucessfully Updated", FlashMessage.Status.SUCCESS));

        return "redirect:/cert_prog";
    }

    @RequestMapping(value = "/school/{schoolId}", method = RequestMethod.POST)
    public String UpdateSchool(@Valid @ModelAttribute School school, BindingResult result, RedirectAttributes redirectAttributes, Principal principal) {
        //User user = userService.findByUsername(principal.getName());
        User user = (User) ((UsernamePasswordAuthenticationToken) principal).getPrincipal();
        school.setUser(user);
        //examService.increment(exams);
        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.newSchool", result);
            //
            redirectAttributes.addFlashAttribute("newSchool", school);
            //redirect back to form
            return String.format("redirect:/cert_prog/%s/edit", school.getId());
        }
        schoolService.save(school);

        //When data passes validation and saved, use flash to indicate it
        redirectAttributes.addFlashAttribute("flash", new FlashMessage("School Information Form have been Sucessfully Updated", FlashMessage.Status.SUCCESS));

        return "redirect:/cert_prog";
    }

    @RequestMapping(value = "cert_prog/{certId}", method = RequestMethod.POST)
    public String UpdateFlag(@PathVariable Long certId, @ModelAttribute CertificateProgramme certificateProgramme) {
        certificateService.flag(certificateProgramme);
        return String.format("redirect:/cert_prog/%s/edit", certId);
    }

    @RequestMapping(path = "/print", method = RequestMethod.GET)
    public String getCertPrint(Model model) {
        return "cert_print";
    }

    @RequestMapping(path = "printRedirect", method = RequestMethod.POST)
    public String CertRedirect(@ModelAttribute CertificateProgramme certificateProgramme, Exams exam, RedirectAttributes redirectAttributes) {
        List<CertificateProgramme> certificateProgrammes = certificateService.findAll();
        List<Exams> examiner = examService.findAll();
        List<School> sch = schoolService.findAll();
        if (certificateProgrammes.size() == 0) {
            redirectAttributes.addFlashAttribute("flash", new FlashMessage("Personal Information has not been filled", FlashMessage.Status.FAILURE));
            return "redirect:/cert_prog";
        }
        if (examiner.size() < 5) {
            redirectAttributes.addFlashAttribute("flash", new FlashMessage("WAEC Information has not been filled", FlashMessage.Status.FAILURE));
            return "redirect:/cert_prog";
        }
        if (sch.size() == 0) {
            redirectAttributes.addFlashAttribute("flash", new FlashMessage("Training Information has not been filled", FlashMessage.Status.FAILURE));
            return "redirect:/cert_prog";
        }

        return "redirect:/print";
    }

    @RequestMapping(value = "downloadPDF", method = RequestMethod.GET)
    public String downloadExcel(Model model) {
        // create some sample data
        List<CertificateProgramme> certificateProgrammes = certificateService.findAll();
        model.addAttribute("certPDF", certificateProgrammes);
        List<Exams> exams = examService.findAll();
        model.addAttribute("certPDF2", exams);
        List<School> schools = schoolService.findAll();
        model.addAttribute("certPDF3", schools);
        return "pdfView";
    }

    /* This method returns a json output,
       Spring provides a functionality in the @Responsebody annotation that converts the output to Json
    */
    @PostMapping(value = "/basicAjax.json")
    @ResponseBody
    public ResponseEntity<Object> addCertJson(@Valid @ModelAttribute CertificateProgramme certificate, BindingResult result, Principal principal) {
        User user = (User) ((UsernamePasswordAuthenticationToken) principal).getPrincipal();
        certificate.setUser(user);
        if (result.hasErrors()) {
            List<String> errors = result.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.toList());
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        } else {
            certificateService.save(certificate);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }
    }

}
