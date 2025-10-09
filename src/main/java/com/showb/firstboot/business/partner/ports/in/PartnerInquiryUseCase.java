package com.showb.firstboot.business.partner.ports.in;

import com.showb.firstboot.business.partner.applications.domains.primary.Partner;

import java.util.List;

public interface PartnerInquiryUseCase {
    List<Partner> getList();
}
