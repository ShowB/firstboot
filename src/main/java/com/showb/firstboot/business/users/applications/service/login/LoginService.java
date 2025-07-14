package com.showb.firstboot.business.users.applications.service.login;

import com.showb.firstboot.business.users.applications.domains.login.LoginRequest;
import com.showb.firstboot.business.users.applications.domains.login.LoginResponse;
import com.showb.firstboot.business.users.applications.domains.login.LoginUser;
import com.showb.firstboot.business.users.applications.port.in.LoginUseCase;
import com.showb.firstboot.business.users.applications.service.login.components.LoginPostProcesor;
import com.showb.firstboot.business.users.applications.service.login.components.LoginValidator;
import com.showb.firstboot.business.users.applications.service.login.components.UserTokenGenerator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LoginService implements LoginUseCase {
    private final LoginValidator loginValidator;
    private final UserTokenGenerator userTokenGenerator;
    private final LoginPostProcesor loginPostProcesor;


    public LoginService(
            LoginValidator loginValidator,
            UserTokenGenerator userTokenGenerator,
            LoginPostProcesor loginPostProcesor
    ) {
        this.loginPostProcesor = loginPostProcesor;
        this.loginValidator = loginValidator;
        this.userTokenGenerator = userTokenGenerator;
    }

    @Override
    @Transactional
    public LoginResponse login(LoginRequest loginRequest) {
        LoginUser loginUser = loginValidator.getValidatedLoginUser(loginRequest);
        String token = userTokenGenerator.generateToken(loginUser);
        loginPostProcesor.processPostLogin(loginUser);

        return LoginResponse.success(token);
    }
}
