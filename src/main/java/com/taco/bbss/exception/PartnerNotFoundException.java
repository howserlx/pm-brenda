package com.taco.bbss.exception;


public class PartnerNotFoundException extends Exception {

    public PartnerNotFoundException(Long id) {
        super("Partner id: " + id);
    }
}
