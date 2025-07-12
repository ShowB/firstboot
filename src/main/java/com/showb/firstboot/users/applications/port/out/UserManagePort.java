package com.showb.firstboot.users.applications.port.out;

import com.showb.firstboot.users.applications.domains.primary.User;

public interface UserManagePort {
    void createUser(User user);
}
