package com.showb.firstboot.business.partner.entities;

import com.showb.firstboot.business.common.entities.AuditingEntity;
import com.showb.firstboot.business.partner.applications.domains.primary.Partner;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "partner")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PartnerEntity extends AuditingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "partner_code")
    private String partnerCode;

    @Column(name = "partner_name")
    private String partnerName;

    @Column(name = "email")
    private String email;

    @Column(name = "tel")
    private String tel;


    @SuppressWarnings({"unused", "java:S107"})
    @Builder
    public PartnerEntity(
            Long id,
            String partnerCode,
            String partnerName,
            String email,
            String tel,
            String createdBy,
            LocalDateTime createdAt,
            String updatedBy,
            LocalDateTime updatedAt
    ) {
        super(createdBy, createdAt, updatedBy, updatedAt);
        this.email = email;
        this.id = id;
        this.partnerCode = partnerCode;
        this.partnerName = partnerName;
        this.tel = tel;
    }

    public static PartnerEntity from(Partner from) {
        return PartnerEntity.builder()
                .id(from.id())
                .partnerCode(from.partnerCode())
                .partnerName(from.partnerName())
                .email(from.email())
                .tel(from.tel())
                .createdBy(from.createdBy())
                .createdAt(from.createdAt())
                .updatedBy(from.updatedBy())
                .updatedAt(from.updatedAt())
                .build();
    }

    public Partner toDomain() {
        return Partner.builder()
                .id(this.id)
                .partnerCode(this.partnerCode)
                .partnerName(this.partnerName)
                .email(this.email)
                .tel(this.tel)
                .createdBy(super.getCreatedBy())
                .createdAt(super.getCreatedAt())
                .updatedBy(super.getUpdatedBy())
                .updatedAt(super.getUpdatedAt())
                .build();
    }
}
