package gh.gov.moh.admissionsportal.service;

import gh.gov.moh.admissionsportal.dao.SchoolDao;
import gh.gov.moh.admissionsportal.model.School;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by molayodecker on 07/02/2017.
 */
@Service
public class SchoolServiceImpl implements SchoolService{
    @Autowired
    private SchoolDao schoolDao;

    @Override
    public Iterable<School> findAll() {
       return schoolDao.findAll();
    }

    @Override
    public School findOne(Long id) {
        return schoolDao.findOne(id);
    }

    @Override
    public School save(School school) {
       return schoolDao.save(school);
    }
}
