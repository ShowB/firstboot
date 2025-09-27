package com.showb.firstboot.business.users.applications.service.login.components;

import com.showb.firstboot.business.users.applications.domains.login.LoginUser;
import com.showb.firstboot.utils.jwt.JwtTokenProvider;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UserTokenGeneratorTest {

    @Mock
    private JwtTokenProvider jwtTokenProvider;

    @InjectMocks
    private UserTokenGenerator userTokenGenerator;


    @Test
    @Order(1)
    @DisplayName("generateTokens 메서드는 accessToken 과 refreshToken 을 생성하여 GeneratedToken 객체로 반환해야 한다.")
    void generateTokens() {
        // Given
        LoginUser loginUser = LoginUser.builder()
                .userId(1L)
                .loginId("loginId")
                .build();

        when(jwtTokenProvider.createAccessToken(loginUser))
                .thenReturn("mockedAccessToken");
        when(jwtTokenProvider.createRefreshToken(loginUser))
                .thenReturn("mockedRefreshToken");

        // When
        UserTokenGenerator.GeneratedToken result = userTokenGenerator.generateTokens(loginUser);

        // Then
        assertEquals("mockedAccessToken", result.accessToken());
        assertEquals("mockedRefreshToken", result.refreshToken());
    }
}
