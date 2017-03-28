package gh.gov.moh.admissionsportal.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by molayodecker on 27/03/2017.
 */
@Controller
public class BrochureController {
    @GetMapping(value = "/certcutoff")
    public String CertCutOff(Model model)
    {
        return "certificate_cutoff";
    }

    @GetMapping(value = "/certcutoffprog")
    public String CertCutOffProg(Model model){
        return "certificate_cutoff_prog";
    }

    @GetMapping(value = "/certcutoffagg")
    public String CertCutOffAgg(Model model){
        return "certificate_cutoff_agg";
    }

    @GetMapping(value = "/diplocutoff")
    public String DiploCutOff(Model model){
        return "dilopma_cutoff";
    }
}
