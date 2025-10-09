package com.showb.firstboot.business.partner.adapters.out.persistences;

import com.showb.firstboot.business.partner.applications.domains.primary.Partner;
import com.showb.firstboot.business.partner.entities.PartnerEntity;
import com.showb.firstboot.business.partner.ports.out.persistences.PartnerPort;
import com.showb.firstboot.business.partner.repositories.PartnerRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PartnerAdapter implements PartnerPort {
    private final PartnerRepository partnerRepository;


    public PartnerAdapter(PartnerRepository partnerRepository) {
        this.partnerRepository = partnerRepository;
    }

    @Override
    public List<Partner> findAll() {
        return partnerRepository.findAll()
                .stream()
                .map(PartnerEntity::toDomain)
                .toList();
    }
}
