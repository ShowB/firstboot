package com.showb.firstboot.helloworld.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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


    @Override
    public String toString() {
        return "FirstbootTestEntity{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
