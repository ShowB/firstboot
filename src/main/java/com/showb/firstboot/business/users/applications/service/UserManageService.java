package com.showb.firstboot.business.users.applications.service;


import com.showb.firstboot.business.users.applications.domains.UserCreate;
import com.showb.firstboot.business.users.applications.domains.primary.User;
import com.showb.firstboot.business.users.applications.port.in.UserManageUseCase;
import com.showb.firstboot.business.users.applications.port.out.UserManagePort;
import com.showb.firstboot.business.users.exceptions.UserManageExceptionType;
import com.showb.firstboot.exceptions.FirstBootException;
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
            throw new FirstBootException(UserManageExceptionType.DUPLICATED_LOGIN_ID, userCreate.loginId());
        }

        User user = userCreate.create();
        userManagePort.createUser(user);
    }
}
