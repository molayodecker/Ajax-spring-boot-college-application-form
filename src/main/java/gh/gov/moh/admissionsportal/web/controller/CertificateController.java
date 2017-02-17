package gh.gov.moh.admissionsportal.web.controller;

import gh.gov.moh.admissionsportal.service.CertificateService;
import gh.gov.moh.admissionsportal.service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * Created by molayodecker on 27/01/2017.
 */
@Controller
public class CertificateController {

    @Autowired
    private ExamService examService;

    @Autowired
    private CertificateService certificateService;




}
