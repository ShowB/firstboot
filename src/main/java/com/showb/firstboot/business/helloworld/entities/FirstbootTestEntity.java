package com.showb.firstboot.business.helloworld.entities;

import com.showb.firstboot.business.users.enums.PersonalCode;
import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name = "firstboot_test")
public class FirstbootTestEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "code", length = 50)
    private String code;

    @Column(name = "value", length = 300)
    private String value;


    private FirstbootTestEntity(String code, String value) {
        this.code = code;
        this.value = value;
    }

    protected FirstbootTestEntity() {

    }

    public static FirstbootTestEntity from(PersonalCode personalCode) {
        return new FirstbootTestEntity(personalCode.name(), personalCode.getHumanName());
    }

    @Override
    public String toString() {
        return "FirstbootTestEntity{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
