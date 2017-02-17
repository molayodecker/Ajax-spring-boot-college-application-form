package gh.gov.moh.admissionsportal.service;

import gh.gov.moh.admissionsportal.model.CertificateProgramme;

import java.util.List;

/**
 * Created by molayodecker on 27/01/2017.
 */
public interface CertificateService {
    List<CertificateProgramme> findAll();
    CertificateProgramme findOne(Long id);
    void save(CertificateProgramme certificateProgramme);
}
