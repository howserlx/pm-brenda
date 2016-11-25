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
import com.taco.bbss.dto.filter.PartnerFilter;
import com.taco.bbss.dto.reduced.PartnerListInfo;
import com.taco.bbss.repository.PartnerViewRepository;
import com.taco.bbss.service.QueryHelper;

@Repository
public class PartnerViewRepositoryImpl implements PartnerViewRepository {

    private static Logger log = LoggerFactory.getLogger(PartnerViewRepositoryImpl.class);

    private EntityManager entityManager;

    private QueryHelper queryHelper;

    @Override
    public List<PartnerListInfo> getAll(PartnerFilter partnerFilter, User user) {
        StringBuilder queryString = new StringBuilder();
        Map<String, Object> dynamicParams = new HashMap<>();

        queryString.append("SELECT new com.taco.bbss.dto.reduced.PartnerListInfo(p) FROM com.taco.bbss.domain.Partner p " +
                " WHERE pu.user = :user ");

        queryHelper.addLikeCondition(partnerFilter.getFullName(), "fullName", queryString, dynamicParams);

        queryString.append(" ORDER BY p.fullName ASC");

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
