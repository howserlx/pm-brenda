package com.taco.bbss.service;

import java.util.List;

import com.taco.bbss.domain.Patient;
import com.taco.bbss.domain.auth.User;
import com.taco.bbss.dto.filter.PatientFilter;
import com.taco.bbss.dto.reduced.PatientListInfo;

public interface PatientService {
    void save(Patient patient, User user);

    List<PatientListInfo> getAll(PatientFilter patientFilter, User user);

    Patient findById(Long id, User user);
}
