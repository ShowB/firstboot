package com.showb.firstboot.business.partner.repositories;

import com.showb.firstboot.business.partner.entities.PartnerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartnerRepository extends JpaRepository<PartnerEntity, Long> {}
