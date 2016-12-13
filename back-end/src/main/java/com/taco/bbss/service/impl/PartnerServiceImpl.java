package com.taco.bbss.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taco.bbss.domain.Partner;
import com.taco.bbss.domain.auth.User;
import com.taco.bbss.dto.filter.PartnerFilter;
import com.taco.bbss.dto.reduced.PartnerListInfo;
import com.taco.bbss.repository.PartnerRepository;
import com.taco.bbss.repository.PartnerViewRepository;
import com.taco.bbss.service.PartnerService;

@Service
public class PartnerServiceImpl implements PartnerService {

    private PartnerRepository partnerRepository;

    private PartnerViewRepository partnerViewRepository;

    @Override
    public Partner findById(Long id, User user) {

        return partnerRepository.findByIdAndUser(id, user);
    }

    @Override
    public List<PartnerListInfo> getAll(PartnerFilter partnerFilter, User user) {
        return partnerViewRepository.getAll(partnerFilter, user);
    }

    @Override
    public void save(Partner partner, User user) {

        partner.setUser(user);

        partnerRepository.save(partner);
    }

    @Autowired
    public void setPartnerRepository(PartnerRepository partnerRepository) {
        this.partnerRepository = partnerRepository;
    }

    @Autowired
    public void setPartnerViewRepository(PartnerViewRepository partnerViewRepository) {
        this.partnerViewRepository = partnerViewRepository;
    }
}
