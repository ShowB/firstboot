package com.showb.firstboot.business.users.applications.service.login.components;

import com.showb.firstboot.business.users.applications.domains.login.LoginUser;
import com.showb.firstboot.business.users.applications.domains.primary.User;
import com.showb.firstboot.business.users.applications.port.out.UserPort;
import org.springframework.stereotype.Service;

@Service
public class LoginPostProcesor {
    private final UserPort userPort;


    public LoginPostProcesor(UserPort userPort) {
        this.userPort = userPort;
    }

    public void processPostLogin(LoginUser loginUser) {
        User user = loginUser.login().toDomain();
        userPort.save(user);
    }
}
