package com.showb.firstboot.business.users.applications.service;


import com.showb.firstboot.business.users.applications.domains.UserCreateRequest;
import com.showb.firstboot.business.users.applications.domains.primary.User;
import com.showb.firstboot.business.users.applications.port.in.UserManageUseCase;
import com.showb.firstboot.business.users.applications.port.out.UserPort;
import com.showb.firstboot.business.users.exceptions.UserManageExceptionType;
import com.showb.firstboot.exceptions.FirstBootException;
import org.springframework.stereotype.Service;

@Service
public class UserManageService implements UserManageUseCase {
    private final UserPort userPort;


    public UserManageService(UserPort userPort) {
        this.userPort = userPort;
    }

    @Override
    public void createUser(UserCreateRequest userCreateRequest) {
        boolean duplicatedLoginId = userPort.findByLoginId(userCreateRequest.loginId())
                .isPresent();

        if (duplicatedLoginId) {
            throw new FirstBootException(UserManageExceptionType.DUPLICATED_LOGIN_ID, userCreateRequest.loginId());
        }

        User user = userCreateRequest.create();
        userPort.save(user);
    }
}
