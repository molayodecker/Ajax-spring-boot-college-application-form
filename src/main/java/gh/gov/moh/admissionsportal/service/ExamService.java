package gh.gov.moh.admissionsportal.service;

import gh.gov.moh.admissionsportal.model.Exams;

import java.util.List;

/**
 * Created by molayodecker on 28/01/2017.
 */
public interface ExamService {
    List<Exams> findAll();

    Exams findOne(Long id);

    void toggleComplete(Long id);

    void save(Exams exams);

    void increment(Exams exams);
}
