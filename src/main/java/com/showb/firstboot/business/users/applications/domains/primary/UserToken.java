package com.showb.firstboot.business.users.applications.domains.primary;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder(toBuilder = true)
public record UserToken(
        Long id,
        Long userId,
        String accessToken,
        String refreshToken,
        LocalDateTime lastRefreshedAt,
        String createdBy,
        LocalDateTime createdAt,
        String updatedBy,
        LocalDateTime updatedAt
) {
    public static UserToken create(long userId) {
        return UserToken.builder()
                .userId(userId)
                .build();
    }

    public UserToken updateTokens(String newAccessToken, String newRefreshToken) {
        return this.toBuilder()
                .accessToken(newAccessToken)
                .refreshToken(newRefreshToken)
                .lastRefreshedAt(LocalDateTime.now())
                .build();
    }

    public boolean isValidForRefresh(String accessToken, String refreshToken) {
        return this.accessToken().equals(accessToken) && this.refreshToken().equals(refreshToken);
    }
}
