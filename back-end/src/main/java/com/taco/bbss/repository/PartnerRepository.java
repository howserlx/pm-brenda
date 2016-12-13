package com.taco.bbss.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.taco.bbss.domain.Partner;
import com.taco.bbss.domain.auth.User;

@Repository
public interface PartnerRepository extends CrudRepository<Partner, Long> {
    Partner findByIdAndUser(Long id, User user);
}
