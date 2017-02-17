package gh.gov.moh.admissionsportal.service;

import gh.gov.moh.admissionsportal.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * Created by molayodecker on 26/01/2017.
 */
public interface UserService extends UserDetailsService {
    User findByUsername(String username);
}
