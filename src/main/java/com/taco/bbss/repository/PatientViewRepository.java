package com.taco.bbss.repository;


import java.util.List;

import com.taco.bbss.domain.auth.User;
import com.taco.bbss.dto.filter.PatientFilter;
import com.taco.bbss.dto.reduced.PatientListInfo;

public interface PatientViewRepository {

    List<PatientListInfo> getAll(PatientFilter patientFilter, User user);
}
