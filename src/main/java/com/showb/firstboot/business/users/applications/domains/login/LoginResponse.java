package com.showb.firstboot.business.users.applications.domains.login;

import com.showb.firstboot.business.users.enums.UserStatus;
import lombok.Builder;

@Builder
public record LoginResponse(
        boolean success,
        UserStatus status,
        String token
) {
    public static LoginResponse success(String token) {
        return LoginResponse.builder()
                .success(true)
                .status(UserStatus.NORMAL)
                .token(token)
                .build();
    }
}
