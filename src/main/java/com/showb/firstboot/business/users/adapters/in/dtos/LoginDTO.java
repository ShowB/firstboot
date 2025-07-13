package com.showb.firstboot.business.users.adapters.in.dtos;

import com.showb.firstboot.business.users.applications.domains.UserLogin;
import com.showb.firstboot.business.users.enums.UserStatus;
import lombok.Builder;

@Builder
public record LoginDTO(
        Request request,
        Response response
) {
    public record Request(
            String loginId,
            String password
    ) {
    }
    @Builder
    public record Response(
            String loginId,
            String password,
            UserStatus status,
            boolean signIn,
            String token,
            boolean initializedPassword,
            boolean requiredPasswordChange
    ) {
        public static Response from(UserLogin from) {
            return Response.builder()
                    .loginId(from.loginId())
                    .password(from.password())
                    .status(from.status())
                    .signIn(from.signIn())
                    .token(from.token())
                    .initializedPassword(from.initializedPassword())
                    .requiredPasswordChange(from.requiredPasswordChange())
                    .build();
        }
    }
}
