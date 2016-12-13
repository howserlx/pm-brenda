package com.taco.bbss.repository.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.taco.bbss.domain.auth.User;
import com.taco.bbss.dto.filter.PatientFilter;
import com.taco.bbss.dto.reduced.PatientListInfo;
import com.taco.bbss.repository.PatientViewRepository;
import com.taco.bbss.service.QueryHelper;

@Repository
public class PatientViewRepositoryImpl implements PatientViewRepository {

    private static Logger log = LoggerFactory.getLogger(PatientViewRepositoryImpl.class);

    private EntityManager entityManager;

    private QueryHelper queryHelper;


    @SuppressWarnings("unchecked")
    public List<PatientListInfo> getAll(PatientFilter patientFilter, User user) {
        StringBuilder queryString = new StringBuilder();
        Map<String, Object> dynamicParams = new HashMap<>();

        queryString.append("SELECT new PatientListInfo(p) FROM PatientByUser pu JOIN pu.patient p " +
                " WHERE pu.user = :user ");

        queryHelper.addLikeCondition(patientFilter.getName(), "names", queryString, dynamicParams);
        queryHelper.addLikeCondition(patientFilter.getLastName1(), "lastName1", queryString, dynamicParams);
        queryHelper.addLikeCondition(patientFilter.getLastName2(), "lastName2", queryString, dynamicParams);
        queryHelper.addBetweenDateCondition(patientFilter.getBirthDateMin(), patientFilter.getBirthDateMax(), "birthdate", queryString, dynamicParams, "ddMMyyyy");

        queryString.append(" ORDER BY p.names ASC");

        javax.persistence.Query query = entityManager.createQuery(queryString.toString());

        query.setParameter("user", user);

        queryHelper.applyConditions(dynamicParams, query);

        return query.getResultList();
    }

    @Autowired
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Autowired
    public void setQueryHelper(QueryHelper queryHelper) {
        this.queryHelper = queryHelper;
    }
}
