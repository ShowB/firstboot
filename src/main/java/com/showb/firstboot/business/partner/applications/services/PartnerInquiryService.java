package com.showb.firstboot.business.partner.applications.services;

import com.showb.firstboot.business.partner.applications.domains.primary.Partner;
import com.showb.firstboot.business.partner.ports.in.PartnerInquiryUseCase;
import com.showb.firstboot.business.partner.ports.out.persistences.PartnerPort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PartnerInquiryService implements PartnerInquiryUseCase {
    private final PartnerPort partnerPort;


    public PartnerInquiryService(PartnerPort partnerPort) {
        this.partnerPort = partnerPort;
    }

    @Override
    public List<Partner> getList() {
        return this.partnerPort.findAll();
    }
}
