package gh.gov.moh.admissionsportal.service;

import gh.gov.moh.admissionsportal.model.UrlLogger;

import java.util.List;

/**
 * Created by molayodecker on 10/03/2017.
 */
public interface LoggerService {
    List<UrlLogger> findAll();
    void save(UrlLogger logger);
    void logger(String logger);
    void loggerURL(String logger, Long id);
    long findOne();
    String findRequest();
    UrlLogger ListAll();
}
