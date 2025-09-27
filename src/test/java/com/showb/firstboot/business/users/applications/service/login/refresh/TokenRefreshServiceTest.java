package com.showb.firstboot.business.users.applications.service.login.refresh;

import com.showb.firstboot.business.users.applications.domains.login.LoginUser;
import com.showb.firstboot.business.users.applications.domains.login.refresh.TokenRefreshResponse;
import com.showb.firstboot.business.users.applications.domains.primary.User;
import com.showb.firstboot.business.users.applications.domains.primary.UserToken;
import com.showb.firstboot.business.users.applications.port.out.UserPort;
import com.showb.firstboot.business.users.applications.port.out.UserTokenPort;
import com.showb.firstboot.business.users.applications.service.login.components.UserTokenGenerator;
import com.showb.firstboot.utils.jwt.JwtTokenProvider;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TokenRefreshServiceTest {

    @Mock
    private JwtTokenProvider jwtTokenProvider;
    @Mock
    private UserTokenPort userTokenPort;
    @Mock
    private UserPort userPort;
    @Mock
    private UserTokenGenerator userTokenGenerator;

    @InjectMocks
    private TokenRefreshService tokenRefreshService;

    @Test
    @DisplayName("성공적인 토큰 갱신")
    void refreshAccessToken_success() {
        // Given
        String oldAccessToken = "old-access-token";
        String oldRefreshToken = "old-refresh-token";
        String newAccessToken = "new-access-token";
        String newRefreshToken = "new-refresh-token";
        String loginId = "testuser";
        long userId = 1L;

        User user = User.builder().id(userId).loginId(loginId).build();
        UserToken userToken = UserToken.builder()
                .userId(userId)
                .accessToken(oldAccessToken)
                .refreshToken(oldRefreshToken)
                .build();
        UserTokenGenerator.GeneratedToken generatedToken = new UserTokenGenerator.GeneratedToken(newAccessToken, newRefreshToken);

        when(jwtTokenProvider.validateToken(oldRefreshToken)).thenReturn(true);
        when(jwtTokenProvider.getLoginIdFromToken(oldRefreshToken)).thenReturn(loginId);
        when(userPort.findByLoginId(loginId)).thenReturn(Optional.of(user));
        when(userTokenPort.findByUserId(userId)).thenReturn(Optional.of(userToken));
        when(userTokenGenerator.generateTokens(any(LoginUser.class))).thenReturn(generatedToken);

        // When
        TokenRefreshResponse response = tokenRefreshService.refreshAccessToken(oldAccessToken, oldRefreshToken);

        // Then
        assertEquals(newAccessToken, response.accessToken());
        assertEquals(newRefreshToken, response.refreshToken());
    }
}
