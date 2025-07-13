package com.showb.firstboot.business.users.applications.domains.login;

import com.showb.firstboot.business.users.enums.UserStatus;
import lombok.Builder;

@Builder
public record LoginRequest(
        String loginId,
        String password,
        UserStatus status,
        boolean signIn,
        String token,
        boolean initializedPassword,
        boolean requiredPasswordChange
) {}
