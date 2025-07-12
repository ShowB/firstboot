package com.showb.firstboot.users.applications.port.in;


import com.showb.firstboot.users.applications.domains.UserCreate;

public interface UserManageUseCase {
    void createUser(UserCreate userCreate);
}

