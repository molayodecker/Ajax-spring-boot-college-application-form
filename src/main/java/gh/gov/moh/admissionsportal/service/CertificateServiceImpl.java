package gh.gov.moh.admissionsportal.service;

import gh.gov.moh.admissionsportal.dao.CertificateDao;
import gh.gov.moh.admissionsportal.model.CertificateProgramme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by molayodecker on 27/01/2017.
 */
@Service
public class CertificateServiceImpl implements CertificateService {

    @Autowired
    private CertificateDao certificateDao;

    @Override
    public List<CertificateProgramme> findAll() {
        return certificateDao.findAll();
    }

    @Override
    public CertificateProgramme findOne(Long id) {
        return certificateDao.findOne(id);
    }


    @Override
    public void save(CertificateProgramme certificateProgramme) {
        certificateDao.save(certificateProgramme);
    }
}
