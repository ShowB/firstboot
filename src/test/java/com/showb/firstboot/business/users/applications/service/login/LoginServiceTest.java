package com.showb.firstboot.business.users.applications.service.login;

import com.showb.firstboot.business.users.applications.domains.login.LoginRequest;
import com.showb.firstboot.business.users.applications.domains.login.LoginUser;
import com.showb.firstboot.business.users.applications.domains.primary.UserToken;
import com.showb.firstboot.business.users.applications.port.out.UserTokenPort;
import com.showb.firstboot.business.users.applications.service.login.components.LoginPostProcesor;
import com.showb.firstboot.business.users.applications.service.login.components.LoginValidator;
import com.showb.firstboot.business.users.applications.service.login.components.UserTokenGenerator;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class LoginServiceTest {
    @Mock
    private LoginValidator loginValidator;

    @Mock
    private UserTokenGenerator userTokenGenerator;

    @Mock
    private LoginPostProcesor loginPostProcesor;

    @Mock
    private UserTokenPort userTokenPort;

    @InjectMocks
    private LoginService loginService;

    @Test
    @Order(1)
    @DisplayName("로그인 유효성 검증, 토큰 생성, 토큰 저장, 로그인 후처리 메서드가 순서대로 호출되어야 한다.")
    void login() {
        // Given
        LoginUser mockLoginUser = LoginUser.builder().userId(1L).build();
        UserTokenGenerator.GeneratedToken mockGeneratedToken = new UserTokenGenerator.GeneratedToken("access", "refresh");
        UserToken mockUserToken = UserToken.create(1L);

        when(loginValidator.getValidatedLoginUser(any(LoginRequest.class)))
                .thenReturn(mockLoginUser);
        when(userTokenGenerator.generateTokens(any(LoginUser.class)))
                .thenReturn(mockGeneratedToken);
        when(userTokenPort.findByUserId(anyLong()))
                .thenReturn(Optional.of(mockUserToken));
        doNothing().when(userTokenPort).saveUserToken(any(UserToken.class));
        doNothing().when(loginPostProcesor).processPostLogin(any(LoginUser.class));

        InOrder inOrder = inOrder(loginValidator, userTokenGenerator, userTokenPort, loginPostProcesor);

        // When & Then
        assertDoesNotThrow(() -> loginService.login(mock(LoginRequest.class)));

        inOrder.verify(loginValidator).getValidatedLoginUser(any());
        inOrder.verify(userTokenGenerator).generateTokens(any());
        inOrder.verify(userTokenPort).findByUserId(anyLong());
        inOrder.verify(userTokenPort).saveUserToken(any());
        inOrder.verify(loginPostProcesor).processPostLogin(any());
    }
}
