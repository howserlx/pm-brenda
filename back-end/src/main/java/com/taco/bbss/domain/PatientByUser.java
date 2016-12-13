package com.taco.bbss.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.taco.bbss.domain.auth.User;

@Entity
@Table(name = "patient_by_user")
public class PatientByUser implements Serializable {

    private static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "patient_by_user_seq")
    @SequenceGenerator(name = "patient_by_user_seq", sequenceName = "patient_by_user_seq", allocationSize = 1)
    private Long id;

    @ManyToOne(targetEntity = User.class, optional = false)
    private User user;

    @ManyToOne(targetEntity = Patient.class, optional = false)
    private Patient patient;

    private String notes;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
