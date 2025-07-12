package com.showb.firstboot.users.applications.port.out;

import com.showb.firstboot.users.applications.domains.primary.User;

import java.util.Optional;

public interface UserManagePort {
    void createUser(User user);
    Optional<User> findByLoginId(String loginId);
}
