package com.showb.firstboot.business.users.applications.port.out;

import com.showb.firstboot.business.users.applications.domains.primary.User;

import java.util.Optional;

public interface UserManagePort {
    void createUser(User user);
    Optional<User> findByLoginId(String loginId);
}
