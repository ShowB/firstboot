package com.showb.firstboot.business.users.adapters.in.dto.request;

import com.showb.firstboot.business.users.applications.domains.login.LoginRequest;
import lombok.Builder;

@Builder
public record LoginRequestDTO(
        String loginId,
        String password
) {
    public LoginRequest toDomain() {
        return LoginRequest.builder()
                .loginId(this.loginId)
                .password(this.password)
                .build();
    }
}
