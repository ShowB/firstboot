package com.showb.firstboot.helloworld.controller;

import com.showb.firstboot.helloworld.entities.FirstbootTestEntity;
import com.showb.firstboot.helloworld.repositories.FirstbootTestRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.SecureRandom;
import java.util.List;
import java.util.Random;

@RestController
public class HelloController {
    private final FirstbootTestRepository firstbootTestRepository;


    public HelloController(FirstbootTestRepository firstbootTestRepository) {
        this.firstbootTestRepository = firstbootTestRepository;
    }

    @GetMapping("/")
    public String hello() {
        Random random = new SecureRandom();

        List<FirstbootTestEntity> entities = firstbootTestRepository.findAll();

        return entities.get(random.nextInt(entities.size())).toString();
    }
}
