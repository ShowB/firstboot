package com.showb.firstboot.business.partner.adapters.in.controllers;

import com.showb.firstboot.business.partner.adapters.in.controllers.dtos.PartnerListDTO;
import com.showb.firstboot.business.partner.applications.domains.primary.Partner;
import com.showb.firstboot.business.partner.ports.in.PartnerInquiryUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/partner/v1")
public class PartnerManageController {
    private final PartnerInquiryUseCase partnerInquiryUseCase;


    public PartnerManageController(PartnerInquiryUseCase partnerInquiryUseCase) {
        this.partnerInquiryUseCase = partnerInquiryUseCase;
    }

    @GetMapping
    public ResponseEntity<PartnerListDTO> getList() {
        List<Partner> list = this.partnerInquiryUseCase.getList();

        return ResponseEntity.ok()
                .body(PartnerListDTO.from(list));
    }
}
