package com.showb.firstboot.business.users.applications.domains.primary;

import org.junit.jupiter.api.*;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UserTokenTest {
    @Test
    @Order(1)
    @DisplayName("create 메서드는 전달된 userId 를 userId 로 설정하여 새로운 자기 자신을 반환해야 한다.")
    void create() {
        UserToken result = UserToken.create(333L);

        assertEquals(333L, result.userId());
    }

    @Test
    @Order(2)
    @DisplayName("refreshToken 메서드는 token 과 lastRefreshedAt 값을 현재 시각으로 설정하여 새로운 자기 자신을 반환해야 한다.")
    void refreshToken() {
        UserToken userToken = UserToken.builder()
                .token("abcde")
                .lastRefreshedAt(LocalDateTime.now()
                        .minusDays(1L))
                .build();

        UserToken result = userToken.refreshToken("newToken");

        assertNotEquals(userToken.token(), result.token());
        assertTrue(result.lastRefreshedAt().isAfter(userToken.lastRefreshedAt()));
        assertEquals("newToken", result.token());
    }
}
