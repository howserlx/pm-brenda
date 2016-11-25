package com.taco.bbss.dto.reduced;


import com.taco.bbss.domain.Partner;

public class PartnerListInfo {

    private Long id;
    private String fullName;
    private String institution;
    private String phoneNumber;

    public PartnerListInfo(Partner partner) {
        this.id = partner.getId();
        this.fullName = partner.getFullName();
        this.institution = partner.getInstitution();
        this.phoneNumber = partner.getPhoneNumber();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getInstitution() {
        return institution;
    }

    public void setInstitution(String institution) {
        this.institution = institution;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
