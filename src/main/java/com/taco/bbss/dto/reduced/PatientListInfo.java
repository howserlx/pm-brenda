package com.taco.bbss.dto.reduced;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;

import com.taco.bbss.domain.Patient;

public class PatientListInfo {

    private static final char SPACE_CHAR = ' ';

    private Long id;
    private String name;
    private int age;
    private String activity;
    private Calendar registerDate;

    public PatientListInfo(Patient patient) {

        LocalDate birthday = LocalDate.ofEpochDay(patient.getBirthday().getTime());

        this.id = patient.getId();
        this.name = patient.getNames() + SPACE_CHAR + patient.getLastName1() + SPACE_CHAR + patient.getLastName2();
        this.age = (int) birthday.until(LocalDate.now(), ChronoUnit.YEARS);
        this.activity = patient.getActivity();
        this.registerDate = patient.getRegisterDate();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public Calendar getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Calendar registerDate) {
        this.registerDate = registerDate;
    }
}
