package gh.gov.moh.admissionsportal.service;

import gh.gov.moh.admissionsportal.dao.ExamsDao;
import gh.gov.moh.admissionsportal.model.Exams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by molayodecker on 28/01/2017.
 */
@Service
public class ExamServiceImpl implements ExamService {

    @Autowired
    private ExamsDao examsDao;

    @Override
    public Iterable<Exams> findAll() {
        return examsDao.findAll();
    }

    @Override
    public Exams findOne(Long id) {
        return examsDao.findOne(id);
    }

    @Override
    public void toggleComplete(Long id) {
        Exams exams = examsDao.findOne(id);
        exams.setComplete(!exams.isComplete());
        examsDao.save(exams);
    }

    @Override
    public void save(Exams exams) {
        examsDao.save(exams);
    }

    @Override
    public void increment(Exams exams) {
        //exams.setCounter(exams.getCounter() + 1);
    }
}
