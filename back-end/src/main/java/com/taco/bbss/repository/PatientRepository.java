package com.taco.bbss.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.taco.bbss.domain.Patient;
import com.taco.bbss.domain.auth.User;

@Repository
public interface PatientRepository extends CrudRepository<Patient, Long> {

    @Query("SELECT p FROM com.taco.bbss.domain.PatientByUser pu JOIN pu.patient p " +
            " WHERE p.id = ?1 AND pu.user = ?2")
    Patient findOne(Long id, User user);
}
