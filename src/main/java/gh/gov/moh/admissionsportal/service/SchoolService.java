package gh.gov.moh.admissionsportal.service;

import gh.gov.moh.admissionsportal.model.School;

/**
 * Created by molayodecker on 07/02/2017.
 */
public interface SchoolService {
    Iterable<School> findAll();
    Object findOne(Long id);
    School save(School school);
}
