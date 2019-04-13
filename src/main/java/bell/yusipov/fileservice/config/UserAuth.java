package bell.yusipov.fileservice.config;

import bell.yusipov.fileservice.dao.UserDao;
import bell.yusipov.fileservice.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserAuth implements UserDetailsService {

    private final UserDao userDao;

    @Autowired
    public UserAuth(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userDao.getUserByName(userName);
        return new UserPrincipal(user);
    }
}