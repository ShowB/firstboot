package com.showb.firstboot.business.users.applications.service.login.components;

import com.showb.firstboot.business.users.applications.domains.login.LoginUser;
import com.showb.firstboot.utils.jwt.JwtTokenProvider;
import lombok.Builder;
import org.springframework.stereotype.Service;

@Service
public class UserTokenGenerator {
    private final JwtTokenProvider jwtTokenProvider;

    @Builder
    public record GeneratedToken(String accessToken, String refreshToken) {}

    public UserTokenGenerator(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    public GeneratedToken generateTokens(LoginUser loginUser) {
        String accessToken = jwtTokenProvider.createAccessToken(loginUser);
        String refreshToken = jwtTokenProvider.createRefreshToken(loginUser);

        return GeneratedToken.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }
}
