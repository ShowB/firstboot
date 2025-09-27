package com.showb.firstboot.business.users.applications.service.login.refresh;

import com.showb.firstboot.business.users.applications.domains.login.LoginUser;
import com.showb.firstboot.business.users.applications.domains.login.refresh.TokenRefreshResponse;
import com.showb.firstboot.business.users.applications.domains.primary.User;
import com.showb.firstboot.business.users.applications.domains.primary.UserToken;
import com.showb.firstboot.business.users.applications.port.in.TokenRefreshUseCase;
import com.showb.firstboot.business.users.applications.port.out.UserPort;
import com.showb.firstboot.business.users.applications.port.out.UserTokenPort;
import com.showb.firstboot.business.users.applications.service.login.components.UserTokenGenerator;
import com.showb.firstboot.business.users.exceptions.LoginExceptionType;
import com.showb.firstboot.exceptions.FirstbootException;
import com.showb.firstboot.utils.jwt.JwtTokenProvider;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TokenRefreshService implements TokenRefreshUseCase {
    private final JwtTokenProvider jwtTokenProvider;
    private final UserTokenPort userTokenPort;
    private final UserPort userPort;
    private final UserTokenGenerator userTokenGenerator;


    public TokenRefreshService(
            JwtTokenProvider jwtTokenProvider,
            UserPort userPort,
            UserTokenGenerator userTokenGenerator,
            UserTokenPort userTokenPort
    ) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.userPort = userPort;
        this.userTokenGenerator = userTokenGenerator;
        this.userTokenPort = userTokenPort;
    }

    @Override
    @Transactional
    public TokenRefreshResponse refreshAccessToken(String accessToken, String refreshToken) {
        // 1. Validate Refresh Token (Signature and Expiration)
        if (!jwtTokenProvider.validateToken(refreshToken)) {
            throw new FirstbootException(LoginExceptionType.INVALID_REFRESH_TOKEN);
        }

        // 2. Get User ID from Refresh Token
        String loginId = jwtTokenProvider.getLoginIdFromToken(refreshToken);

        // 3. Get User Info
        User user = userPort.findByLoginId(loginId)
                .orElseThrow(() -> new FirstbootException(LoginExceptionType.USER_NOT_FOUND));

        // 4. Find UserToken from DB
        UserToken userToken = userTokenPort.findByUserId(user.id())
                .orElseThrow(() -> new FirstbootException(LoginExceptionType.REFRESH_TOKEN_NOT_FOUND));

        // 5. Verify if the received tokens match the stored ones via UserToken domain
        if (!userToken.isValidForRefresh(accessToken, refreshToken)) {
            throw new FirstbootException(LoginExceptionType.INVALID_ACCESS_TOKEN);
        }

        // 7. Create LoginUser domain for token generation
        LoginUser loginUser = LoginUser.from(user);

        // 8. Generate new tokens
        UserTokenGenerator.GeneratedToken generatedToken = userTokenGenerator.generateTokens(loginUser);

        // 9. Update UserToken with the new tokens
        UserToken updatedUserToken = userToken.updateTokens(generatedToken.accessToken(), generatedToken.refreshToken());
        userTokenPort.saveUserToken(updatedUserToken);

        // 10. Return new tokens
        return TokenRefreshResponse.builder()
                .accessToken(generatedToken.accessToken())
                .refreshToken(generatedToken.refreshToken())
                .build();
    }
}
