package com.showb.firstboot.business.helloworld.repositories;

import com.showb.firstboot.business.helloworld.entities.FirstbootTestEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FirstbootTestRepository extends JpaRepository<FirstbootTestEntity, Long> {}
