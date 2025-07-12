package com.showb.firstboot.users.adapters.in.dtos;

import com.showb.firstboot.users.applications.domains.UserCreate;

public record UserCreateDTO(
        String name,
        String loginId,
        String password
) {
    public UserCreate toDomain() {
        return new UserCreate(this.name, this.loginId, this.password);
    }
}
