package com.showb.firstboot.business.users.applications.service.login;

import com.showb.firstboot.business.users.applications.domains.login.LoginRequest;
import com.showb.firstboot.business.users.applications.domains.login.LoginResponse;
import com.showb.firstboot.business.users.applications.domains.login.LoginUser;
import com.showb.firstboot.business.users.applications.domains.primary.UserToken;
import com.showb.firstboot.business.users.applications.port.in.LoginUseCase;
import com.showb.firstboot.business.users.applications.port.out.UserTokenPort;
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
    private final UserTokenPort userTokenPort;


    public LoginService(
            LoginValidator loginValidator,
            UserTokenGenerator userTokenGenerator,
            LoginPostProcesor loginPostProcesor,
            UserTokenPort userTokenPort
    ) {
        this.loginPostProcesor = loginPostProcesor;
        this.loginValidator = loginValidator;
        this.userTokenGenerator = userTokenGenerator;
        this.userTokenPort = userTokenPort;
    }

    @Override
    @Transactional
    public LoginResponse login(LoginRequest loginRequest) {
        LoginUser loginUser = loginValidator.getValidatedLoginUser(loginRequest);
        UserTokenGenerator.GeneratedToken generatedToken = userTokenGenerator.generateTokens(loginUser);

        UserToken userToken = userTokenPort.findByUserId(loginUser.userId())
                .orElse(UserToken.create(loginUser.userId()));

        userToken = userToken.updateTokens(generatedToken.accessToken(), generatedToken.refreshToken());
        userTokenPort.saveUserToken(userToken);

        loginPostProcesor.processPostLogin(loginUser);

        return LoginResponse.success(generatedToken.accessToken(), generatedToken.refreshToken());
    }
}
