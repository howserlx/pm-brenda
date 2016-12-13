package com.taco.bbss.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taco.bbss.domain.Patient;
import com.taco.bbss.domain.PatientByUser;
import com.taco.bbss.domain.auth.User;
import com.taco.bbss.dto.filter.PatientFilter;
import com.taco.bbss.dto.reduced.PatientListInfo;
import com.taco.bbss.repository.PatientByUserRepository;
import com.taco.bbss.repository.PatientRepository;
import com.taco.bbss.repository.PatientViewRepository;
import com.taco.bbss.service.PatientService;

@Service
public class PatientServiceImpl implements PatientService {

    private PatientRepository patientRepository;

    private PatientViewRepository patientViewRepository;

    private PatientByUserRepository patientByUserRepository;

    @Override
    @Transactional
    public void save(Patient patient, User user) {
        boolean isNew = patient.getId() == null;

        patientRepository.save(patient);

        if (isNew) {
            PatientByUser patientByUser = new PatientByUser();

            patientByUser.setUser(user);
            patientByUser.setPatient(patient);

            patientByUserRepository.save(patientByUser);
        }

    }

    @Override
    public List<PatientListInfo> getAll(PatientFilter patientFilter, User user) {
        return patientViewRepository.getAll(patientFilter, user);
    }

    @Override
    public Patient findById(Long id, User user) {
        return patientRepository.findOne(id, user);
    }

    @Autowired
    public void setPatientRepository(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Autowired
    public void setPatientByUserRepository(PatientByUserRepository patientByUserRepository) {
        this.patientByUserRepository = patientByUserRepository;
    }

    @Autowired
    public void setPatientViewRepository(PatientViewRepository patientViewRepository) {
        this.patientViewRepository = patientViewRepository;
    }
}
