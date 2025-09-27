package com.showb.firstboot.configs.jpa;

import com.showb.firstboot.utils.jwt.JwtTokenProvider;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.AuditorAware;
import org.springframework.lang.NonNull;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Optional;

@RequiredArgsConstructor
public class AuditorAwareImpl implements AuditorAware<String> {

    private final JwtTokenProvider jwtTokenProvider;

    @NonNull
    @Override
    public Optional<String> getCurrentAuditor() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes == null) {
            return Optional.of("SYSTEM_USER");
        }

        HttpServletRequest request = attributes.getRequest();
        String token = jwtTokenProvider.resolveToken(request);

        if (StringUtils.hasText(token)) {
            try {
                if(jwtTokenProvider.validateToken(token)) {
                    String loginId = jwtTokenProvider.getLoginIdFromToken(token);
                    return Optional.of(loginId);
                }
            } catch (Exception e) {
                // 토큰 파싱 실패시 SYSTEM_USER 리턴
            }
        }

        return Optional.of("SYSTEM_USER");
    }
}