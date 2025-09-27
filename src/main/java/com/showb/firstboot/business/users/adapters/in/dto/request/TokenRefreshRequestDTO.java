package com.showb.firstboot.business.users.adapters.in.dto.request;

import jakarta.validation.constraints.NotBlank;

public record TokenRefreshRequestDTO(
        @NotBlank
        String refreshToken
) {
}
