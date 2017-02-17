package gh.gov.moh.admissionsportal.service;

import gh.gov.moh.admissionsportal.dao.UserDao;
import gh.gov.moh.admissionsportal.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Created by molayodecker on 26/01/2017.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User findByUsername(String username) {
        return userDao.findByUsername(username) ;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //Load the user from the database(Throw a not found Exception)
        User user = userDao.findByUsername(username);
        if(user == null){
            throw new UsernameNotFoundException("User not found");
        }
        //Return the user Object
        return user;
    }
}


