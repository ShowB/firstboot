package com.showb.firstboot.business.users.applications.domains;

import com.showb.firstboot.business.users.applications.domains.primary.User;

public record UserCreateRequest(
        String name,
        String loginId,
        String plainPassword
) {
    public User create() {
        return User.create(this.name, this.loginId, this.plainPassword);
    }
}
