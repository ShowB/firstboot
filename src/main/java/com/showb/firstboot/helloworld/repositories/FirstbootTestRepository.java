package com.showb.firstboot.helloworld.repositories;

import com.showb.firstboot.helloworld.entities.FirstbootTestEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FirstbootTestRepository extends JpaRepository<FirstbootTestEntity, Long> {}
