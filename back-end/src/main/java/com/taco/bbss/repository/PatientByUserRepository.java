package com.taco.bbss.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.taco.bbss.domain.PatientByUser;

@Repository
public interface PatientByUserRepository extends CrudRepository<PatientByUser, Long> {
}
