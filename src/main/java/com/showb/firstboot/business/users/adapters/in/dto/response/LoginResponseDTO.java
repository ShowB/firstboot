package com.showb.firstboot.business.users.adapters.in.dto.response;

import com.showb.firstboot.business.users.applications.domains.login.LoginResponse;
import com.showb.firstboot.business.users.enums.UserStatus;
import lombok.Builder;

@Builder
public record LoginResponseDTO(
        boolean success,
        UserStatus status,
        String token
) {
    public static LoginResponseDTO from(LoginResponse from) {
        return LoginResponseDTO.builder()
                .success(from.success())
                .status(from.status())
                .token(from.token())
                .build();
    }
}
