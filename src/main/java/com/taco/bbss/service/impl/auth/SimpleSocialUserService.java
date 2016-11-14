package com.taco.bbss.service.impl.auth;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.social.security.SocialUserDetailsService;
import org.springframework.stereotype.Service;

import com.taco.bbss.domain.auth.User;
import com.taco.bbss.domain.auth.UserDetails;
import com.taco.bbss.repository.UserRepository;

@Service
public class SimpleSocialUserService implements SocialUserDetailsService {
    private final Logger log = LoggerFactory.getLogger(SimpleSocialUserService.class);

    private UserRepository userRepository;

    @Override
    public SocialUserDetails loadUserByUserId(String userId) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(userId);

        if (user == null) {
            log.info("Username not found.");
            throw new UsernameNotFoundException("Username not found");
        }

        return new UserDetails.Builder(user).build();
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
