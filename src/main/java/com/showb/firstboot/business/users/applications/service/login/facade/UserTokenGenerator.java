
package com.showb.firstboot.business.users.applications.service.login.facade;

import com.showb.firstboot.business.users.applications.domains.login.LoginUser;
import com.showb.firstboot.business.users.applications.domains.primary.UserToken;
import com.showb.firstboot.business.users.applications.port.out.UserTokenPort;
import com.showb.firstboot.utils.jwt.JwtTokenProvider;
import org.springframework.stereotype.Service;

@Service
public class UserTokenGenerator {
    private final UserTokenPort userTokenPort;
    private final JwtTokenProvider jwtTokenProvider;


    public UserTokenGenerator(UserTokenPort userTokenPort, JwtTokenProvider jwtTokenProvider) {
        this.userTokenPort = userTokenPort;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    public String generateToken(LoginUser loginUser) {
        String token = jwtTokenProvider.createToken(loginUser);

        UserToken userToken = userTokenPort.findByUserId(loginUser.userId())
                .orElse(UserToken.create(loginUser.userId()));

        userToken = userToken.refreshToken(token);
        userTokenPort.saveUserToken(userToken);

        return token;
    }
}
