package com.showb.firstboot.business.users.applications.port.in;


import com.showb.firstboot.business.users.applications.domains.UserCreateRequest;

public interface UserManageUseCase {
    void createUser(UserCreateRequest userCreateRequest);
}

