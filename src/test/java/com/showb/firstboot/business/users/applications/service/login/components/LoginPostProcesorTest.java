package com.showb.firstboot.business.users.applications.service.login.components;

import com.showb.firstboot.business.users.applications.domains.login.LoginUser;
import com.showb.firstboot.business.users.applications.port.out.UserPort;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.inOrder;

@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class LoginPostProcesorTest {
    @Mock
    private UserPort userPort;

    @InjectMocks
    private LoginPostProcesor loginPostProcesor;

    @Test
    @Order(1)
    @DisplayName("processPostLogin 메서드는 로그인 후 유저 정보를 후처리 하여 저장해야 한다.")
    void processPostLogin() {
        LoginUser loginUser = LoginUser.builder()
                .build();

        assertDoesNotThrow(() -> loginPostProcesor.processPostLogin(loginUser));

        InOrder inOrder = inOrder(userPort);
        inOrder.verify(userPort).save(any());
    }
}
