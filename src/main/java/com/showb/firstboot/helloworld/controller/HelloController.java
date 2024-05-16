package com.showb.firstboot.helloworld.controller;

import com.showb.firstboot.helloworld.entities.FirstbootTestEntity;
import com.showb.firstboot.helloworld.enums.PersonalCode;
import com.showb.firstboot.helloworld.repositories.FirstbootTestRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.SecureRandom;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Random;
import java.util.stream.Collectors;

@RestController
public class HelloController {
    private final FirstbootTestRepository firstbootTestRepository;


    public HelloController(FirstbootTestRepository firstbootTestRepository) {
        this.firstbootTestRepository = firstbootTestRepository;
    }

    @GetMapping("/who-is-poor")
    public String hello() {
        Random random = new SecureRandom();

        List<FirstbootTestEntity> entities = firstbootTestRepository.findAll();

        return entities.get(random.nextInt(entities.size())).toString();
    }

    @PostMapping("/poor/{name}")
    public void addWeight(@PathVariable String name) {
        PersonalCode personalCode = PersonalCode.from(name);

        if (Objects.isNull(personalCode)) {
            return;
        }

        FirstbootTestEntity entity = FirstbootTestEntity.from(personalCode);
        firstbootTestRepository.save(entity);

    }

    @GetMapping("/poor/list")
    public Map<String, Long> getPoors() {
        return firstbootTestRepository.findAll()
                .stream()
                .collect(Collectors.groupingBy(FirstbootTestEntity::getValue, Collectors.counting()));
    }
}
