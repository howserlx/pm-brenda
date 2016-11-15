package com.taco.bbss.dto.filter;

public class PatientFilter {

    private String name;
    private String lastName1;
    private String lastName2;
    private String birthDateMin; //ddmmyyyy
    private String birthDateMax; //ddmmyyyy

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getBirthDateMin() {
        return birthDateMin;
    }

    public void setBirthDateMin(String birthDateMin) {
        this.birthDateMin = birthDateMin;
    }

    public String getBirthDateMax() {
        return birthDateMax;
    }

    public void setBirthDateMax(String birthDateMax) {
        this.birthDateMax = birthDateMax;
    }
    
}
