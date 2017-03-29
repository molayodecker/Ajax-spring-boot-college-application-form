package gh.gov.moh.admissionsportal.service;

import gh.gov.moh.admissionsportal.dao.LoggerDao;
import gh.gov.moh.admissionsportal.model.UrlLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by molayodecker on 10/03/2017.
 */
@Service
public class LoggerServiceImpl implements LoggerService {

    @Autowired
    LoggerDao loggerDao;

    @Override
    public List<UrlLogger> findAll() {
        return loggerDao.findAll() ;
    }

    @Override
    public void save(UrlLogger logger) {
      loggerDao.save(logger);
    }

    @Override
    public void logger(String logger) {
        loggerDao.log(logger);
    }

    @Override
    public void loggerURL(String logger, Long id) { loggerDao.logURI(logger, id);
    }

    @Override
    public long findOne() {
        UrlLogger logger = loggerDao.ListAll();
        return logger.getId();
    }

    @Override
    public String findRequest() {
        UrlLogger logger = loggerDao.ListAll();
        return logger.getRedirect();
    }

    @Override
    public UrlLogger ListAll() {
        return loggerDao.ListAll();
    }


}
