package gh.gov.moh.admissionsportal.dao;

import gh.gov.moh.admissionsportal.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends CrudRepository<User, Long> {
    User findByUsername(String username);
}

