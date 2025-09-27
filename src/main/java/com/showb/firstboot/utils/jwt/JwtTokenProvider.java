package com.showb.firstboot.utils.jwt;

import com.showb.firstboot.business.users.applications.domains.login.LoginUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Base64;
import java.util.Date;

@Component
public class JwtTokenProvider {
    private final String secretKey;
    private final long accessTokenExpirationSeconds;
    private final long refreshTokenExpirationSeconds;


    public JwtTokenProvider(
            @Value("${jwt.secret:FIRSTBOOT_SECRET_KEY}") String secretKey,
            @Value("${jwt.access-token-expiration-seconds:600}") long accessTokenExpirationSeconds,
            @Value("${jwt.refresh-token-expiration-seconds:3600}") long refreshTokenExpirationSeconds
    ) {
        this.secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
        this.accessTokenExpirationSeconds = accessTokenExpirationSeconds;
        this.refreshTokenExpirationSeconds = refreshTokenExpirationSeconds;
    }

    public String createAccessToken(LoginUser user) {
        return createToken(user, accessTokenExpirationSeconds);
    }

    public String createRefreshToken(LoginUser user) {
        return createToken(user, refreshTokenExpirationSeconds);
    }

    private String createToken(LoginUser user, long expirationSeconds) {
        Claims claims = Jwts.claims();

        claims.put("loginId", user.loginId());
        claims.put("name", user.name());
        claims.put("companyId", user.companyId());

        Date now = new Date();
        Date validity = new Date(now.getTime() + expirationSeconds * 1000);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .compact();
    }

    public String getLoginIdFromToken(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().get("loginId", String.class);
    }

    public String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    public boolean validateToken(String token) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return !claims.getBody().getExpiration().before(new Date());
        } catch (Exception e) {
            return false;
        }
    }
}
