package com.showb.firstboot.business.partner.ports.out.persistences;

import com.showb.firstboot.business.partner.applications.domains.primary.Partner;

import java.util.List;

public interface PartnerPort {
    List<Partner> findAll();
}
