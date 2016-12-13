package com.taco.bbss.service;

import java.util.List;

import com.taco.bbss.domain.Partner;
import com.taco.bbss.domain.auth.User;
import com.taco.bbss.dto.filter.PartnerFilter;
import com.taco.bbss.dto.reduced.PartnerListInfo;

public interface PartnerService {
    Partner findById(Long id, User user);

    List<PartnerListInfo> getAll(PartnerFilter partnerFilter, User user);

    void save(Partner partner, User user);
}
