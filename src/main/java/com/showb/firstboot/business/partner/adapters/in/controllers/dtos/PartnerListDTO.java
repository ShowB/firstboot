package com.showb.firstboot.business.partner.adapters.in.controllers.dtos;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

public record PartnerListDTO(
        List<Partner> partners
) {
    public static PartnerListDTO from(List<com.showb.firstboot.business.partner.applications.domains.primary.Partner> froms) {
        return new PartnerListDTO(
                froms.stream()
                        .map(Partner::from)
                        .toList()
        );
    }

    @Builder
    public record Partner(
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
        public static Partner from(com.showb.firstboot.business.partner.applications.domains.primary.Partner from) {
            return Partner.builder()
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
    }
}
