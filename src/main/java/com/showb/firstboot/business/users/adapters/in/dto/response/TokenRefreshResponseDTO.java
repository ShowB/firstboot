package com.showb.firstboot.business.users.adapters.in.dto.response;

import com.showb.firstboot.business.users.applications.domains.login.refresh.TokenRefreshResponse;
import lombok.Builder;

@Builder
public record TokenRefreshResponseDTO(
        String accessToken,
        String refreshToken
) {
    public static TokenRefreshResponseDTO from(TokenRefreshResponse response) {
        return TokenRefreshResponseDTO.builder()
                .accessToken(response.accessToken())
                .refreshToken(response.refreshToken())
                .build();
    }
}
