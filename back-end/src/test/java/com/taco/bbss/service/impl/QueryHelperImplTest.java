package com.taco.bbss.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Query;

import org.junit.Test;

import com.taco.bbss.service.QueryHelper;

public class QueryHelperImplTest {

    public static final String FIELD_NAME_LAST_NAME = "lastName";
    private static final String DATE_PATTERN = "ddMMyyyy";
    private static final String FIELD_NAME_BIRTH_DATE = "birthdate";

    @Test
    public void applyConditions() {
        QueryHelper queryHelper = new QueryHelperImpl();
        Query query = mock(Query.class);
        Map<String, Object> dynamicParams = new HashMap<>();

        Calendar now = Calendar.getInstance();

        String field0 = "param1";
        String field1 = "param2";
        Date field2 = now.getTime();
        now.add(Calendar.YEAR, 2);
        Date field3 = now.getTime();

        dynamicParams.put("__field0", field0);
        dynamicParams.put("__field1", field1);
        dynamicParams.put("__field2", field2);
        dynamicParams.put("__field3", field3);

        queryHelper.applyConditions(dynamicParams, query);

        verify(query, times(1)).setParameter("__field0", field0);
        verify(query, times(1)).setParameter("__field1", field1);
        verify(query, times(1)).setParameter("__field2", field2);
        verify(query, times(1)).setParameter("__field3", field3);


    }


    @Test
    public void addBetweenDateCondition_minDateNull_mustNotAffectCurrentQuery() {
        QueryHelper queryHelper = new QueryHelperImpl();
        Map<String, Object> dynamicParams = new HashMap<>();
        StringBuilder queryString = new StringBuilder();

        String before = queryString.toString();

        queryHelper.addBetweenDateCondition(null, "25112016", FIELD_NAME_BIRTH_DATE, queryString, dynamicParams, DATE_PATTERN);

        String after = queryString.toString();

        assertEquals("Query must not be affected if init range is null", before, after);
        assertEquals("dynamicParams must not be affected if value is null", 0, dynamicParams.size());
    }

    @Test
    public void addBetweenDateCondition_maxDateNull_mustNotAffectCurrentQuery() {
        QueryHelper queryHelper = new QueryHelperImpl();
        Map<String, Object> dynamicParams = new HashMap<>();
        StringBuilder queryString = new StringBuilder();

        String before = queryString.toString();

        queryHelper.addBetweenDateCondition("25112016", null, FIELD_NAME_BIRTH_DATE, queryString, dynamicParams, DATE_PATTERN);

        String after = queryString.toString();

        assertEquals("Query must not be affected if end range is null", before, after);
        assertEquals("dynamicParams must not be affected if value is null", 0, dynamicParams.size());
    }

    @Test
    public void addBetweenDateCondition_minAndMaxDateNull_mustNotAffectCurrentQuery() {
        QueryHelper queryHelper = new QueryHelperImpl();
        Map<String, Object> dynamicParams = new HashMap<>();
        StringBuilder queryString = new StringBuilder();

        String before = queryString.toString();

        queryHelper.addBetweenDateCondition(null, null, FIELD_NAME_BIRTH_DATE, queryString, dynamicParams, DATE_PATTERN);

        String after = queryString.toString();

        assertEquals("Query must not be affected if any range is null", before, after);
    }

    @Test
    public void addBetweenDateCondition_minAndMaxDateAreValid_QueryMustContainBetweenCondition() {
        QueryHelper queryHelper = new QueryHelperImpl();
        Map<String, Object> dynamicParams = new HashMap<>();
        StringBuilder queryString = new StringBuilder();

        String minDate = "01012016";
        String maxDate = "31122017";


        queryHelper.addBetweenDateCondition(minDate, maxDate, FIELD_NAME_BIRTH_DATE, queryString, dynamicParams, DATE_PATTERN);

        String expected = " AND birthdate BETWEEN :__field0 AND :__field1";
        String result = queryString.toString();

        assertEquals("Query consider values as prepared statement", expected, result);
        assertEquals("dynamicParams is expected to contains 2 parameters", 2, dynamicParams.size());
        assertTrue("dynamicParams is expected to contains key __field1", dynamicParams.containsKey("__field0"));
        assertTrue("dynamicParams is expected to contains key __field1", dynamicParams.containsKey("__field1"));
        assertTrue("__field1 value is expected to be a Date object", dynamicParams.get("__field0") instanceof Date);
        assertTrue("__field2 value is expected to be a Date object", dynamicParams.get("__field1") instanceof Date);

        Calendar minDateObject = Calendar.getInstance();
        minDateObject.setTime((Date) dynamicParams.get("__field0"));

        Calendar maxDateObject = Calendar.getInstance();
        maxDateObject.setTime((Date) dynamicParams.get("__field1"));

        assertEquals("Wrong day in " + minDate, 1, minDateObject.get(Calendar.DATE));
        assertEquals("Wrong month in " + minDate, Calendar.JANUARY, minDateObject.get(Calendar.MONTH));
        assertEquals("Wrong year in " + minDate, 2016, minDateObject.get(Calendar.YEAR));

        assertEquals("Wrong day in " + maxDate, 31, maxDateObject.get(Calendar.DATE));
        assertEquals("Wrong month in " + maxDate, Calendar.DECEMBER, maxDateObject.get(Calendar.MONTH));
        assertEquals("Wrong year in " + maxDate, 2017, maxDateObject.get(Calendar.YEAR));
    }

    @Test
    public void addLikeCondition_valueNullMustNotAffectCurrentQuery() {
        QueryHelper queryHelper = new QueryHelperImpl();
        Map<String, Object> dynamicParams = new HashMap<>();
        StringBuilder queryString = new StringBuilder();

        String before = queryString.toString();

        queryHelper.addLikeCondition(null, FIELD_NAME_LAST_NAME, queryString, dynamicParams);

        String after = queryString.toString();

        assertEquals("Query must not be affected if value is null", before, after);
        assertEquals("dynamicParams must not be affected if value is null", 0, dynamicParams.size());
    }

    @Test
    public void addLikeCondition_valueIsNotNull_ItMustBeConsideredInQuery() {
        QueryHelper queryHelper = new QueryHelperImpl();
        Map<String, Object> dynamicParams = new HashMap<>();
        StringBuilder queryString = new StringBuilder();

        queryHelper.addLikeCondition("SomeLastName", FIELD_NAME_LAST_NAME, queryString, dynamicParams);

        String expected = " AND " + FIELD_NAME_LAST_NAME + " LIKE %:__field0%";
        String result = queryString.toString();

        assertEquals("Query must not be affected if value is null", expected, result);
        assertEquals("dynamicParams must contains the value", 1, dynamicParams.size());
        assertTrue("dynamicParams must contains key __field0", dynamicParams.containsKey("__field0"));
        assertEquals("value of __field0 in dynamicParams must be SomeLastName", "SomeLastName", dynamicParams.get("__field0"));
    }
}
