package com.showb.firstboot.business.users.applications.service.login.facade;

import com.showb.firstboot.business.users.applications.domains.login.LoginRequest;
import com.showb.firstboot.business.users.applications.domains.login.LoginUser;
import com.showb.firstboot.business.users.applications.domains.primary.User;
import com.showb.firstboot.business.users.applications.port.out.UserPort;
import com.showb.firstboot.business.users.exceptions.LoginExceptionType;
import com.showb.firstboot.exceptions.FirstBootException;
import com.showb.firstboot.utils.encrypt.HashingUtils;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class LoginValidatorTest {
    @Mock
    private UserPort userPort;

    @InjectMocks
    private LoginValidator loginValidator;

    @Test
    @Order(1)
    @DisplayName("getValidatedLoginUser 메서드는 login_id 로 유저 정보가 조회되지 않으면 예외가 발생해야 한다.")
    void getValidatedLoginUserWhenUserNotFoundByLoginId() {
        LoginRequest loginRequest = LoginRequest.builder()
                .loginId("loginId")
                .build();

        when(userPort.findByLoginId("loginId"))
                .thenReturn(Optional.empty());

        FirstBootException exception = assertThrows(
                FirstBootException.class,
                () -> loginValidator.getValidatedLoginUser(loginRequest)
        );

        assertEquals(LoginExceptionType.FAILED_TO_LOGIN, exception.getExceptionType());
    }

    @Test
    @Order(2)
    @DisplayName("getValidatedLoginUser 메서드는 패스워드가 일치하지 않으면 예외가 발생해야 한다.")
    void getValidatedLoginUserWhenPasswordIsNotMatched() {
        LoginRequest loginRequest = LoginRequest.builder()
                .loginId("loginId")
                .password("abcde")
                .build();

        when(userPort.findByLoginId("loginId"))
                .thenReturn(
                        Optional.of(
                                User.builder()
                                        .password("something hashed value")
                                        .build()

                        )
                );

        FirstBootException exception = assertThrows(
                FirstBootException.class,
                () -> loginValidator.getValidatedLoginUser(loginRequest)
        );

        assertEquals(LoginExceptionType.FAILED_TO_LOGIN, exception.getExceptionType());
    }

    @Test
    @Order(3)
    @DisplayName("getValidatedLoginUser 메서드는 유저 정보가 존재하고, 패스워드가 일치하면 LoginUser 객체를 반환해야 한다.")
    void getValidatedLoginUser() {
        String hashedPassword = HashingUtils.hashingSHA512("abcde");

        LoginRequest loginRequest = LoginRequest.builder()
                .loginId("loginId")
                .password("abcde")
                .build();

        when(userPort.findByLoginId("loginId"))
                .thenReturn(
                        Optional.of(
                                User.builder()
                                        .password(hashedPassword)
                                        .build()

                        )
                );

        LoginUser loginUser = loginValidator.getValidatedLoginUser(loginRequest);

        assertNotNull(loginUser);
    }
}
