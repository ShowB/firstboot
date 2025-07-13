package com.showb.firstboot.business.users.applications.domains.primary;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder(toBuilder = true)
public record UserToken(
        Long id,
        Long userId,
        String token,
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

    public UserToken refreshToken(String newToken) {
        return this.toBuilder()
                .token(newToken)
                .lastRefreshedAt(LocalDateTime.now())
                .build();
    }
}
