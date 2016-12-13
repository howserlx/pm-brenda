package com.taco.bbss.domain.auth;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.social.security.SocialUser;

public class UserDetails extends SocialUser {

    private User user;
    private SocialMediaService socialSignInProvider;

    public UserDetails(User user, Collection<? extends GrantedAuthority> authorities) {
        super(user.getEmail(), "", authorities);

        this.user = user;
    }

    public Long getId() {
        return user.getId();
    }

    public String getFirstName() {
        return user.getFirstName();
    }

    public String getLastName() {
        return user.getLastName();
    }

    public Role getRole() {
        return user.getRole();
    }

    public boolean isAdmin() {
        return user.getRole().equals(Role.ADMIN);
    }

    public String getImageUrl() {
        return user.getPhotoUri();
    }

    public User getUser() {
        return user;
    }

    public String getFullName() {
        return getFirstName() + " " + getLastName();
    }

    public SocialMediaService getSocialSignInProvider() {
        return user.getSignInProvider();
    }

    @Override
    public String toString() {
        return "UserDetails {" +
                "Name ='" + getFirstName() + ' ' + getLastName() + '\'' +
                ", email='" + getUsername() + '\'' +
                ", role=" + getRole() +
                '}';
    }

    public static class Builder {

        private User user;
        private Set<GrantedAuthority> authorities;


        public Builder(User user) {
            this.user = user;
            this.authorities = new HashSet<>();

            SimpleGrantedAuthority authority = new SimpleGrantedAuthority(user.getRole().toString());
            this.authorities.add(authority);
        }

        public UserDetails build() {
            UserDetails details = new UserDetails(user, authorities);

            return details;
        }
    }
}