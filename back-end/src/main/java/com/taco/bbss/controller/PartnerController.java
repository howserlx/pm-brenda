package com.taco.bbss.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.taco.bbss.domain.Partner;
import com.taco.bbss.domain.auth.UserDetails;
import com.taco.bbss.dto.filter.PartnerFilter;
import com.taco.bbss.dto.reduced.PartnerListInfo;
import com.taco.bbss.exception.PartnerNotFoundException;
import com.taco.bbss.service.PartnerService;

@Controller
@RequestMapping(value = "/partner")
public class PartnerController {

    private static final Logger log = LoggerFactory.getLogger(PartnerController.class);

    private PartnerService partnerService;

    @Autowired
    public void setPartnerService(PartnerService partnerService) {
        this.partnerService = partnerService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Partner getPartner(@RequestParam Long id, @AuthenticationPrincipal UserDetails userDetails)
            throws PartnerNotFoundException {

        Partner partner = partnerService.findById(id, userDetails.getUser());

        if (partner == null) {
            throw new PartnerNotFoundException(id);
        }

        return partner;
    }

    @RequestMapping(value = "all", method = RequestMethod.GET)
    public List<PartnerListInfo> getAll(@RequestBody PartnerFilter partnerFilter,
                                        @AuthenticationPrincipal UserDetails userDetails) {

        log.debug("Search partners: {}", partnerFilter);

        return partnerService.getAll(partnerFilter, userDetails.getUser());
    }

    @RequestMapping(method = RequestMethod.POST)
    public Long savePartner(@RequestBody Partner partner, @AuthenticationPrincipal UserDetails userDetails) {

        partnerService.save(partner, userDetails.getUser());

        return partner.getId();
    }
}
