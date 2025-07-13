package com.showb.firstboot.business.users.adapters.in.dto.request;

import com.showb.firstboot.business.users.applications.domains.UserCreateRequest;

public record UserCreateRequestDTO(
        String name,
        String loginId,
        String password
) {
    public UserCreateRequest toDomain() {
        return new UserCreateRequest(this.name, this.loginId, this.password);
    }
}
