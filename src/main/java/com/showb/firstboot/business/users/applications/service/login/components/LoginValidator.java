package com.showb.firstboot.business.users.applications.service.login.components;

import com.showb.firstboot.business.users.applications.domains.login.LoginRequest;
import com.showb.firstboot.business.users.applications.domains.login.LoginUser;
import com.showb.firstboot.business.users.applications.port.out.UserPort;
import com.showb.firstboot.business.users.exceptions.LoginExceptionType;
import com.showb.firstboot.exceptions.FirstbootException;
import org.springframework.stereotype.Service;

@Service
public class LoginValidator {
    private final UserPort userPort;


    public LoginValidator(UserPort userPort) {
        this.userPort = userPort;
    }

    public LoginUser getValidatedLoginUser(LoginRequest loginRequest) {
        LoginUser loginUser = userPort.findByLoginId(loginRequest.loginId())
                .map(LoginUser::from)
                .orElseThrow(() -> new FirstbootException(LoginExceptionType.FAILED_TO_LOGIN));

        if (loginUser.nonMatchedPassword(loginRequest.password())) {
            throw new FirstbootException(LoginExceptionType.FAILED_TO_LOGIN);
        }

        return loginUser;
    }
}
