package com.showb.firstboot.business.users.applications.domains;

import com.showb.firstboot.business.users.applications.domains.primary.User;
import com.showb.firstboot.business.users.enums.UserStatus;
import lombok.Builder;

@Builder
public record UserLogin(
        String loginId,
        String password,
        UserStatus status,
        boolean signIn,
        String token,
        boolean initializedPassword,
        boolean requiredPasswordChange
) {
    public static UserLogin of(User from, boolean signIn, boolean requiredPasswordChange, String token) {
        return UserLogin.builder()
                .loginId(from.loginId())
                .password(from.password())
                .status(from.status())
                .signIn(signIn)
                .token(token)
                .initializedPassword(from.pwInit())
                .requiredPasswordChange(requiredPasswordChange)
                .build();
    }
}
