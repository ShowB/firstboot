package com.showb.firstboot.business.partner.applications.domains.primary;

import lombok.Builder;

import java.time.LocalDateTime;

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
) {}
