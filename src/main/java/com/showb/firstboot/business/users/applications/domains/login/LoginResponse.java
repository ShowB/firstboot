package com.showb.firstboot.business.users.applications.domains.login;

import com.showb.firstboot.business.users.enums.UserStatus;
import lombok.Builder;

@Builder
public record LoginResponse(
        boolean success,
        UserStatus status,
        String accessToken,
        String refreshToken
) {
    public static LoginResponse success(String accessToken, String refreshToken) {
        return LoginResponse.builder()
                .success(true)
                .status(UserStatus.NORMAL)
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }
}
