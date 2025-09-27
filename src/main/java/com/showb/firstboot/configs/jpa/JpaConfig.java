package com.showb.firstboot.configs.jpa;

import com.showb.firstboot.utils.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing
@RequiredArgsConstructor
public class JpaConfig {

    private final JwtTokenProvider jwtTokenProvider;

    @Bean
    public AuditorAware<String> auditorProvider() {
        return new AuditorAwareImpl(jwtTokenProvider);
    }
}