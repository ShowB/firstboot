package com.showb.firstboot.business.users.applications.domains.login;

import org.junit.jupiter.api.*;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class LoginUserTest {
    @Test
    @Order(1)
    @DisplayName("login 메서드는 lastAccessedAt 값을 현재 시각으로 설정하여 새로운 자기 자신을 반환해야 한다.")
    void login() {
        LoginUser loginUser = LoginUser.builder()
                .lastAccessedAt(LocalDateTime.now().minusDays(1L))
                .build();

        LoginUser newLoginUser = loginUser.login();

        assertNotEquals(loginUser.lastAccessedAt(), newLoginUser.lastAccessedAt());
        assertTrue(newLoginUser.lastAccessedAt().isAfter(loginUser.lastAccessedAt()));
    }
}
