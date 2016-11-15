package com.taco.bbss.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.taco.bbss.domain.auth.User;

@Entity
@Table(name = "partner")
public class Partner implements Serializable {

    private static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "partner_seq")
    @SequenceGenerator(name = "partner_seq", sequenceName = "partner_seq", allocationSize = 1)
    private Long id;

    @Column(name = "full_name", length = 200)
    private String fullName;

    @Column(name = "institution", length = 100)
    private String institution;

    @Column(name = "phone_number", length = 80)
    private String phoneNumber;

    @Column(name = "email", length = 250)
    private String email;

    @ManyToOne(targetEntity = User.class, optional = false)
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getInstitution() {
        return institution;
    }

    public void setInstitution(String institution) {
        this.institution = institution;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
