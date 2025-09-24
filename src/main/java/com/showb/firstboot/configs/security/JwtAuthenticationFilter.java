package com.showb.firstboot.configs.security;

import com.showb.firstboot.business.users.entites.UserEntity;
import com.showb.firstboot.business.users.entites.UserTokenEntity;
import com.showb.firstboot.business.users.exceptions.LoginExceptionType;
import com.showb.firstboot.business.users.repositories.UserRepository;
import com.showb.firstboot.business.users.repositories.UserTokenRepository;
import com.showb.firstboot.exceptions.FirstbootException;
import com.showb.firstboot.utils.jwt.JwtTokenProvider;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtTokenProvider jwtTokenProvider;
    private final UserTokenRepository userTokenRepository;
    private final UserRepository userRepository;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = jwtTokenProvider.resolveToken(request);

        if (!StringUtils.hasText(token)) {
            return;
        }

        if (this.jwtTokenProvider.validateToken(token)) {
            String loginId = this.jwtTokenProvider.getLoginId(token);

            UserEntity userEntity = this.userRepository.findByLoginId(loginId)
                    .orElseThrow(() -> new FirstbootException(LoginExceptionType.FAILED_TO_LOGIN));

            UserTokenEntity userTokenEntity = this.userTokenRepository.findByUserId(userEntity.getId())
                    .orElseThrow(() -> new FirstbootException(LoginExceptionType.FAILED_TO_LOGIN));

            if (token.equals(userTokenEntity.getToken())) {
                this.setAuthentication(userEntity);
            }
        }

        filterChain.doFilter(request, response);
    }

    private void setAuthentication(UserEntity userEntity) {
        UserDetails userDetails = new User(userEntity.getLoginId(), "", Collections.emptyList());
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}
