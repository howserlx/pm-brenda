package com.taco.bbss.exception;

public class PatientNotFoundException extends Exception {
    public PatientNotFoundException(Long id) {
        super("Patient id: " + id);
    }
}
