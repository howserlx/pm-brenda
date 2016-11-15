package com.taco.bbss.domain;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedDate;

@Entity
@Table(name = "patient")
public class Patient implements Serializable {

    private static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "patient_seq")
    @SequenceGenerator(name = "patient_seq", sequenceName = "patient_seq", allocationSize = 1)
    private Long id;

    @Column(name = "names", length = 100)
    private String names;

    @Column(name = "last_name1", length = 100)
    private String lastName1;

    @Column(name = "last_name2", length = 100)
    private String lastName2;

    @Column(name = "birthday")
    @Temporal(TemporalType.DATE)
    private Date birthday;

    @Enumerated(EnumType.STRING)
    @Column(name = "marital_status", length = 25)
    private MaritalStatus maritalStatus;

    @Column(name = "activity", length = 255)
    private String activity;

    @Column(name = "husband_name", length = 255)
    private String husbandName;

    @Column(name = "husband_birthday")
    @Temporal(TemporalType.DATE)
    private Date husbandBirthday;

    @Column(name = "husband_activity", length = 255)
    private String husbandActivity;

    @Column(name = "number_of_children")
    private Integer numberOfChildren;

    @Column(name = "address", length = 300)
    private String address;

    @Column(name = "phone_number", length = 80)
    private String phoneNumber;

    @Column(name = "email", length = 250)
    private String email;

    @CreatedDate
    @Column(name = "register_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar registerDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }

    public String getLastName1() {
        return lastName1;
    }

    public void setLastName1(String lastName1) {
        this.lastName1 = lastName1;
    }

    public String getLastName2() {
        return lastName2;
    }

    public void setLastName2(String lastName2) {
        this.lastName2 = lastName2;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public MaritalStatus getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(MaritalStatus maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public String getHusbandName() {
        return husbandName;
    }

    public void setHusbandName(String husbandName) {
        this.husbandName = husbandName;
    }

    public Date getHusbandBirthday() {
        return husbandBirthday;
    }

    public void setHusbandBirthday(Date husbandBirthday) {
        this.husbandBirthday = husbandBirthday;
    }

    public String getHusbandActivity() {
        return husbandActivity;
    }

    public void setHusbandActivity(String husbandActivity) {
        this.husbandActivity = husbandActivity;
    }

    public Integer getNumberOfChildren() {
        return numberOfChildren;
    }

    public void setNumberOfChildren(Integer numberOfChildren) {
        this.numberOfChildren = numberOfChildren;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public Calendar getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Calendar registerDate) {
        this.registerDate = registerDate;
    }
}
