package com.taco.bbss.repository;


import java.util.List;

import com.taco.bbss.domain.auth.User;
import com.taco.bbss.dto.filter.PartnerFilter;
import com.taco.bbss.dto.reduced.PartnerListInfo;

public interface PartnerViewRepository {

    List<PartnerListInfo> getAll(PartnerFilter partnerFilter, User user);
}
