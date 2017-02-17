package gh.gov.moh.admissionsportal.web.controller;

import gh.gov.moh.admissionsportal.model.*;
import gh.gov.moh.admissionsportal.service.*;
import gh.gov.moh.admissionsportal.web.Grade;
import gh.gov.moh.admissionsportal.web.Region;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.security.Principal;

/**
 * Created by molayodecker on 28/01/2017.
 */
@Controller
public class ExamsController {
    

    @Autowired
    private ExamService examService;

    @Autowired
    private SchoolService schoolService;

    @Autowired
    private UserService userService;

    @Autowired
    private CertificateService certificateService;

    @Autowired
    private PictureService pictureService;

    @RequestMapping(value = "/cert_prog", method = RequestMethod.GET)
    public String examsList(Model model){
        Iterable<Exams> exams = examService.findAll();
        Iterable<School> schools = schoolService.findAll();
        Iterable<CertificateProgramme> certificateProgrammes = certificateService.findAll();
        Iterable<Picture> pictures = pictureService.findAll();
        model.addAttribute("exams", exams);

        if(!model.containsAttribute("newExam")){
            model.addAttribute("newExam", new Exams());
        }

        model.addAttribute("certificateProgrammes", certificateProgrammes);
        if(!model.containsAttribute("certificate")){
            model.addAttribute("certificate",new CertificateProgramme());
        }
        model.addAttribute("grades", Grade.values());
        model.addAttribute("regions", Region.values());
        model.addAttribute("schools",schools);
        if(!model.containsAttribute("newSchool")){
            model.addAttribute("newSchool",new School());
        }

        model.addAttribute("picture", new Picture());
        model.addAttribute("regions", Region.values());
        return "cert_prog";
    }

    @RequestMapping(path = "/mark", method = RequestMethod.POST)
    public String toggleComplete(@RequestParam Long id) {
        Exams exams = examService.findOne(id);
        examService.toggleComplete(id);
        return "redirect:/cert_prog";
    }

    @RequestMapping(value = "/exams", method = RequestMethod.POST)
    public String addTask(@Valid @ModelAttribute Exams exams, BindingResult result, RedirectAttributes redirectAttributes, Principal principal){
        //User user = userService.findByUsername(principal.getName());
        User user = (User)((UsernamePasswordAuthenticationToken)principal).getPrincipal();
        exams.setUser(user);
        //examService.increment(exams);
        if(result.hasErrors()){
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.newExam", result);
            //
            redirectAttributes.addFlashAttribute("newExam",exams);
            //redirect back to form
            return "redirect:/cert_prog";
        }
        examService.save(exams);

        //When data passes validation and saved, use flash to indicate it
        redirectAttributes.addFlashAttribute("flash",new FlashMessage("Form Sucessfully Submitted", FlashMessage.Status.SUCCESS));

        return "redirect:/cert_prog";
    }


    @RequestMapping(value = "/school", method = RequestMethod.POST)
    public String addSchool(@ModelAttribute School school, BindingResult result, Principal principal, RedirectAttributes redirectAttributes){
        User user = (User)((UsernamePasswordAuthenticationToken)principal).getPrincipal();
        school.setUser(user);

        if(result.hasErrors()){
           redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.newSchool", result);

           redirectAttributes.addFlashAttribute("newSchool",school);

            //redirect back to page
            return "redirect:/cert_prog";
        }
          schoolService.save(school);
        //When data passes validation and saved, use flash to indicate it
        redirectAttributes.addFlashAttribute("flash",new FlashMessage("Form Sucessfully Submitted", FlashMessage.Status.SUCCESS));
        return "redirect:/cert_prog";
    }

    @RequestMapping(value = "/basic", method = RequestMethod.POST)
    public String addCertificateForm(@Valid @ModelAttribute CertificateProgramme certificate, BindingResult result, RedirectAttributes redirectAttributes, Principal principal){
        User user = (User)((UsernamePasswordAuthenticationToken)principal).getPrincipal();

        certificate.setUser(user);

        if(result.hasErrors()){
            //Include error validation upon redirect
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.certificate", result);

            //Add data if invalid data is received
            redirectAttributes.addFlashAttribute("certificate",certificate);

            //redirect back to page
            return "redirect:/cert_prog";
        }
        certificateService.save(certificate);

        //When data passes validation and saved, use flash to indicate it
        redirectAttributes.addFlashAttribute("flash",new FlashMessage("Form Sucessfully Submitted", FlashMessage.Status.SUCCESS));
        return "redirect:/cert_prog";
    }

    @RequestMapping("/img/{pictureId}.gif")
    @ResponseBody
    public byte[] pictureImage(@PathVariable Long pictureId){
        return pictureService.findById(pictureId).getBytes();
    }


    @RequestMapping(value = "/pictures", method = RequestMethod.POST)
    public String addPicture(Picture picture, @RequestParam MultipartFile file, RedirectAttributes redirectAttributes){
        //TODO: Upload if file is valid
        pictureService.save(picture,file);

        //Add a flash Message on successful upload
        redirectAttributes.addFlashAttribute("flash", new FlashMessage("File Uploaded Successfully", FlashMessage.Status.SUCCESS));

        //TODO:Redirect after file upload
        return String.format("redirect:/%s",picture.getId());
    }

    //Form for editing personal information
    @RequestMapping("cert_prog/")
    public String editCertificateForm(){
    return null;
    }
}
