package com.taco.bbss.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.taco.bbss.service.QueryHelper;

@Component
public class QueryHelperImpl implements QueryHelper {

    private static Logger log = LoggerFactory.getLogger(QueryHelperImpl.class);

    public void applyConditions(Map<String, Object> dynamicParams, Query query) {
        for (Map.Entry<String, Object> entry : dynamicParams.entrySet()) {
            query.setParameter(entry.getKey(), entry.getValue());
        }
    }

    public void addBetweenDateCondition(String rangeDateMin, String rangeDateMax, String fieldName, StringBuilder queryString, Map<String, Object> dynamicParams, String datePattern) {

        if (rangeDateMin != null && rangeDateMax != null) {
            SimpleDateFormat sdf = new SimpleDateFormat(datePattern);

            try {
                Date minValue = sdf.parse(rangeDateMin);
                Date maxValue = sdf.parse(rangeDateMax);
                String dynamicFieldMin = "__field" + dynamicParams.size();
                String dynamicFieldMax = "__field" + (dynamicParams.size() + 1);

                queryString.append(" AND ");
                queryString.append(fieldName);
                queryString.append(" BETWEEN :").append(dynamicFieldMin);
                queryString.append(" AND :").append(dynamicFieldMax);

                dynamicParams.put(dynamicFieldMin, minValue);
                dynamicParams.put(dynamicFieldMax, maxValue);
            } catch (ParseException e) {
                log.error("Error parsing parameter " + fieldName, e);
            }
        }
    }

    public void addLikeCondition(String value, String fieldName, StringBuilder queryString, Map<String, Object> dynamicParams) {
        if (value != null) {
            String dynamicField = "__field" + dynamicParams.size();

            queryString.append(" AND ");
            queryString.append(fieldName);
            queryString.append(" LIKE %:").append(dynamicField).append("%");

            dynamicParams.put(dynamicField, value);
        }
    }
}
