package com.caesar_84.lunchvoter.service.implementations;

import com.caesar_84.lunchvoter.LoggedUser;
import com.caesar_84.lunchvoter.domain.User;
import com.caesar_84.lunchvoter.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Logging in.
 */
@Service("userDetailsService")
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {
    @Autowired
    private UserRepository repository;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        String lcEmail = email.toLowerCase();
        User user = repository.getByEmail(lcEmail);
        logger.info("Authenticating user: " + email);

        if (null == user) {
            throw new UsernameNotFoundException(lcEmail + "not found.");
        }
        if (!user.isEnabled()) {
            throw new DisabledException(lcEmail + "is disabled on this resource. Please, contact the administrator.");
        }

        return new LoggedUser(user);
    }
}
