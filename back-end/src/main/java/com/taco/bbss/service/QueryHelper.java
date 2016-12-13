package com.taco.bbss.service;

import java.util.Map;

import javax.persistence.Query;

public interface QueryHelper {

    void applyConditions(Map<String, Object> dynamicParams, Query query);

    void addBetweenDateCondition(String rangeDateMin, String rangeDateMax, String fieldName, StringBuilder queryString, Map<String, Object> dynamicParams, String datePattern);

    void addLikeCondition(String value, String fieldName, StringBuilder queryString, Map<String, Object> dynamicParams);

}
