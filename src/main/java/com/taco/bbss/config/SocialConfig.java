package com.taco.bbss.config;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.social.UserIdSource;
import org.springframework.social.config.annotation.ConnectionFactoryConfigurer;
import org.springframework.social.config.annotation.EnableSocial;
import org.springframework.social.config.annotation.SocialConfigurer;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;
import org.springframework.social.google.connect.GoogleConnectionFactory;
import org.springframework.social.security.AuthenticationNameUserIdSource;

import com.taco.bbss.service.impl.auth.AccountConnectionSignUpService;

@Configuration
@EnableSocial
public class SocialConfig implements SocialConfigurer {

    private final Logger log = LoggerFactory.getLogger(SocialConfig.class);

    @Autowired
    private DataSource dataSource;

    @Autowired
    private AccountConnectionSignUpService accountConnectionSignUpService;

    @Override
    public void addConnectionFactories(ConnectionFactoryConfigurer connectionFactoryConfigurer, Environment environment) {

        log.info("Binding Google Connection Factory");

        connectionFactoryConfigurer.addConnectionFactory(new GoogleConnectionFactory(
                environment.getProperty("spring.social.google.client-id"),
                environment.getProperty("spring.social.google.client-secret")
        ));
    }

    @Override
    public UserIdSource getUserIdSource() {
        return new AuthenticationNameUserIdSource();
    }

    @Override
    public UsersConnectionRepository getUsersConnectionRepository(ConnectionFactoryLocator connectionFactoryLocator) {
        JdbcUsersConnectionRepository repository = new JdbcUsersConnectionRepository(dataSource, connectionFactoryLocator, Encryptors.noOpText());

        log.info("Setting implicit signing up");

        repository.setConnectionSignUp(accountConnectionSignUpService);
        return repository;
    }

}
