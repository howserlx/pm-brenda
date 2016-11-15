package com.taco.bbss.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.taco.bbss.config.SocialConfig;
import com.taco.bbss.domain.Patient;
import com.taco.bbss.domain.auth.UserDetails;
import com.taco.bbss.dto.filter.PatientFilter;
import com.taco.bbss.dto.reduced.PatientListInfo;
import com.taco.bbss.exception.PatientNotFoundException;
import com.taco.bbss.service.PatientService;

@RestController
@RequestMapping(value = "/patient/")
public class PatientController {

    private final Logger log = LoggerFactory.getLogger(SocialConfig.class);

    private PatientService patientService;

    @RequestMapping(value = "all", method = RequestMethod.GET)
    public List<PatientListInfo> getAll(@RequestBody PatientFilter patientFilter,
                                        @AuthenticationPrincipal UserDetails userDetails) {

        log.debug("Search patients: " + patientFilter);

        return patientService.getAll(patientFilter, userDetails.getUser());
    }

    @RequestMapping(method = RequestMethod.GET)
    public Patient getPatient(@RequestParam Long id, @AuthenticationPrincipal UserDetails userDetails)
            throws PatientNotFoundException {

        Patient patient = patientService.findById(id, userDetails.getUser());

        if (patient == null) {
            throw new PatientNotFoundException();
        }

        return patient;
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public Long savePatient(@RequestBody Patient patient, @AuthenticationPrincipal UserDetails userDetails) {

        patientService.save(patient, userDetails.getUser());

        return patient.getId();
    }


    @Autowired
    public void setPatientService(PatientService patientService) {
        this.patientService = patientService;
    }
}
