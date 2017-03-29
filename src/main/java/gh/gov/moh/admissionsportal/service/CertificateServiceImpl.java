package gh.gov.moh.admissionsportal.service;

import gh.gov.moh.admissionsportal.dao.CertificateDao;
import gh.gov.moh.admissionsportal.model.CertificateProgramme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by molayodecker on 27/01/2017.
 */
@Service
@Transactional
public class CertificateServiceImpl implements CertificateService {

    @Autowired
    private CertificateDao certificateDao;

    @Override
    public List<CertificateProgramme> findAll() {
        return certificateDao.findAll();
    }

    @Override
    public CertificateProgramme ListAll() {
        return certificateDao.ListAll();
    }

    @Override
    public CertificateProgramme findOne(Long id) {
        return certificateDao.findOne(id);
    }


    @Override
    public void save(CertificateProgramme certificateProgramme) {
        certificateDao.save(certificateProgramme);
    }

    @Override
    public CertificateProgramme flagger(CertificateProgramme certificateProgramme) {
        certificateProgramme.setFlag(true);
        return certificateProgramme;
    }

    @Override
    public CertificateProgramme unFlagger(CertificateProgramme certificateProgramme) {
        certificateProgramme.setFlag(false);
        certificateDao.save(certificateProgramme);
        return certificateProgramme;
    }

    @Override
    public CertificateProgramme flag(CertificateProgramme certificateProgramme) {
        certificateDao.falseFlagger(certificateProgramme.getId());
        return certificateProgramme;
    }

}
