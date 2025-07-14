package com.showb.firstboot.business.users.applications.service.login;

import com.showb.firstboot.business.users.applications.domains.login.LoginRequest;
import com.showb.firstboot.business.users.applications.domains.login.LoginUser;
import com.showb.firstboot.business.users.applications.service.login.components.LoginPostProcesor;
import com.showb.firstboot.business.users.applications.service.login.components.LoginValidator;
import com.showb.firstboot.business.users.applications.service.login.components.UserTokenGenerator;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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

    @InjectMocks
    private LoginService loginService;

    @Test
    @Order(1)
    @DisplayName("로그인 유효성 검증, 토큰 생성, 로그인 후처리 메서드가 순서대로 호출되어야 한다.")
    void login() {
        when(loginValidator.getValidatedLoginUser(any()))
                .thenReturn(mock(LoginUser.class));
        when(userTokenGenerator.generateToken(any()))
                .thenReturn("mockedToken");
        doNothing().when(loginPostProcesor).processPostLogin(any());

        InOrder inOrder = inOrder(loginValidator, userTokenGenerator, loginPostProcesor);

        assertDoesNotThrow(() -> loginService.login(mock(LoginRequest.class)));

        inOrder.verify(loginValidator).getValidatedLoginUser(any());
        inOrder.verify(userTokenGenerator).generateToken(any());
        inOrder.verify(loginPostProcesor).processPostLogin(any());
    }
}
