package com.showb.firstboot.users.applications.service;


import com.showb.firstboot.exceptions.FirstBootException;
import com.showb.firstboot.users.applications.domains.UserCreate;
import com.showb.firstboot.users.applications.domains.primary.User;
import com.showb.firstboot.users.applications.port.in.UserManageUseCase;
import com.showb.firstboot.users.applications.port.out.UserManagePort;
import org.springframework.stereotype.Service;

@Service
public class UserManageService implements UserManageUseCase {
    private final UserManagePort userManagePort;


    public UserManageService(UserManagePort userManagePort) {
        this.userManagePort = userManagePort;
    }

    @Override
    public void createUser(UserCreate userCreate) {
        boolean duplicatedLoginId = userManagePort.findByLoginId(userCreate.loginId())
                .isPresent();

        if (duplicatedLoginId) {
            throw new FirstBootException("이미 사용중인 ID 입니다.");
        }

        User user = userCreate.create();
        userManagePort.createUser(user);
    }
}
