package com.showb.firstboot.business.users.applications.service.login.components;

import com.showb.firstboot.business.users.applications.domains.login.LoginUser;
import com.showb.firstboot.business.users.applications.port.out.UserTokenPort;
import com.showb.firstboot.utils.jwt.JwtTokenProvider;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UserTokenGeneratorTest {
    @Mock
    private UserTokenPort userTokenPort;

    @Mock
    private JwtTokenProvider jwtTokenProvider;

    @InjectMocks
    private UserTokenGenerator userTokenGenerator;


    @Test
    @Order(1)
    @DisplayName("generateToken 메서드는 토큰을 새로 생성하여 user_token 테이블에 저장하고, 생성한 토큰 값을 반환하여야 한다.")
    void generateToken() {
        LoginUser loginUser = LoginUser.builder()
                .userId(1L)
                .loginId("loginId")
                .build();

        when(jwtTokenProvider.createToken(loginUser))
                .thenReturn("mockedToken");

        String result = userTokenGenerator.generateToken(loginUser);

        InOrder inOrder = inOrder(userTokenPort);
        inOrder.verify(userTokenPort).saveUserToken(any());

        assertEquals("mockedToken", result);
    }
}
