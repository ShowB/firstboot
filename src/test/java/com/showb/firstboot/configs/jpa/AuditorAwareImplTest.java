package com.showb.firstboot.configs.jpa;

import com.showb.firstboot.utils.jwt.JwtTokenProvider;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AuditorAwareImplTest {

    @Mock
    private JwtTokenProvider jwtTokenProvider;

    @Mock
    private HttpServletRequest httpServletRequest;

    @InjectMocks
    private AuditorAwareImpl auditorAware;

    @BeforeEach
    void setUp() {
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(httpServletRequest));
    }

    @Test
    @DisplayName("유효한 토큰이 있을 때 loginId를 반환한다")
    void getCurrentAuditor_withValidToken() {
        // given
        String token = "valid-token";
        String loginId = "testuser";

        when(jwtTokenProvider.resolveToken(httpServletRequest)).thenReturn(token);
        when(jwtTokenProvider.validateToken(token)).thenReturn(true);
        when(jwtTokenProvider.getLoginIdFromToken(token)).thenReturn(loginId);

        // when
        Optional<String> currentAuditor = auditorAware.getCurrentAuditor();

        // then
        assertEquals(Optional.of(loginId), currentAuditor);
    }

    @Test
    @DisplayName("토큰이 없을 때 SYSTEM_USER를 반환한다")
    void getCurrentAuditor_withNoToken() {
        // given
        when(jwtTokenProvider.resolveToken(httpServletRequest)).thenReturn(null);

        // when
        Optional<String> currentAuditor = auditorAware.getCurrentAuditor();

        // then
        assertEquals(Optional.of("SYSTEM_USER"), currentAuditor);
    }

    @Test
    @DisplayName("유효하지 않은 토큰이 있을 때 SYSTEM_USER를 반환한다")
    void getCurrentAuditor_withInvalidToken() {
        // given
        String token = "invalid-token";

        when(jwtTokenProvider.resolveToken(httpServletRequest)).thenReturn(token);
        when(jwtTokenProvider.validateToken(token)).thenReturn(false);

        // when
        Optional<String> currentAuditor = auditorAware.getCurrentAuditor();

        // then
        assertEquals(Optional.of("SYSTEM_USER"), currentAuditor);
    }

    @Test
    @DisplayName("RequestAttributes가 없을 때 SYSTEM_USER를 반환한다")
    void getCurrentAuditor_noRequestAttributes() {
        // given
        RequestContextHolder.setRequestAttributes(null);

        // when
        Optional<String> currentAuditor = auditorAware.getCurrentAuditor();

        // then
        assertEquals(Optional.of("SYSTEM_USER"), currentAuditor);
    }
    
    @Test
    @DisplayName("토큰 파싱 중 예외 발생 시 SYSTEM_USER를 반환한다")
    void getCurrentAuditor_whenParsingThrowsException() {
        // given
        String token = "valid-token-but-fails";
        when(jwtTokenProvider.resolveToken(httpServletRequest)).thenReturn(token);
        when(jwtTokenProvider.validateToken(token)).thenThrow(new RuntimeException("Token parsing failed"));

        // when
        Optional<String> currentAuditor = auditorAware.getCurrentAuditor();

        // then
        assertEquals(Optional.of("SYSTEM_USER"), currentAuditor);
    }
}