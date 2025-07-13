package com.showb.firstboot.utils.jwt;

import com.showb.firstboot.business.users.applications.domains.login.LoginUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.Date;

@Component
@PropertySource("classpath:common-${spring.profiles.active}.properties")
public class JwtTokenProvider {
    private final String secretKey;
    private final long timeout;


    public JwtTokenProvider(
            @Value("${token.secret:FIRSTBOOT_SECRET_KEY}") String secretKey,
            @Value("${token.timeout:600}") long timeout
    ) {
        this.secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
        this.timeout = timeout;
    }

    public String createToken(LoginUser user) {
        Claims claims = Jwts.claims();

        claims.put("loginId", user.loginId());
        claims.put("name", user.name());
        claims.put("companyId", user.companyId());

        Date now = new Date();
        Date validity = new Date(now.getTime() + timeout * 1000);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .compact();
    }
}
