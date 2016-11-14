package com.taco.bbss.service.impl.auth;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.social.connect.UserProfile;
import org.springframework.stereotype.Service;

import com.taco.bbss.domain.auth.Role;
import com.taco.bbss.domain.auth.SocialMediaService;
import com.taco.bbss.domain.auth.User;
import com.taco.bbss.repository.UserRepository;

@Service
public class AccountConnectionSignUpService implements ConnectionSignUp {

    private static final Logger log = LoggerFactory.getLogger(AccountConnectionSignUpService.class);

    private UserRepository userRepository;

    @Override
    public String execute(Connection<?> connection) {
        UserProfile userProfile = connection.fetchUserProfile();

        log.info("Implicit signing up. Reading the data from the provider and creating a user with this");

        User user = userRepository.findByEmail(userProfile.getEmail());

        if (user == null) {
            user = new User();

            user.setFirstName(userProfile.getFirstName());
            user.setLastName(userProfile.getLastName());
            user.setEmail(userProfile.getEmail());
            user.setSignInProvider(SocialMediaService.valueOf(connection.getKey().getProviderId().toUpperCase()));
            user.setPhotoUri(connection.getImageUrl());
            user.setRole(Role.DOCTOR);

            userRepository.save(user);
        }

        return user.getEmail();
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
