package com.showb.firstboot.business.users.applications.port.in;


import com.showb.firstboot.business.users.applications.domains.UserCreate;

public interface UserManageUseCase {
    void createUser(UserCreate userCreate);
}

