package com.taco.bbss.service;

import com.taco.bbss.domain.Patient;

public interface PatientUtils {

    String getFullName(Patient patient);

    int getAge(Patient patient);
}
