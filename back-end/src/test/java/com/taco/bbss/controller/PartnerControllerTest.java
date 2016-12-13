package com.taco.bbss.controller;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.taco.bbss.domain.Partner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("dev")
public class PartnerControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    @Ignore
    public void getPartner_() {
        Partner p = restTemplate.getForObject("/partner?id=1", Partner.class);

        assertThat(p).isEqualTo(null);
    }
}
