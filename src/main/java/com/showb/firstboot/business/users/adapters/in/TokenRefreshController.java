package com.showb.firstboot.business.users.adapters.in;

import com.showb.firstboot.business.users.adapters.in.dto.request.TokenRefreshRequestDTO;
import com.showb.firstboot.business.users.adapters.in.dto.response.TokenRefreshResponseDTO;
import com.showb.firstboot.business.users.applications.port.in.TokenRefreshUseCase;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Auth", description = "인증 관련 API")
@RestController
@RequestMapping("/api/v1/auth")
public class TokenRefreshController {
    private final TokenRefreshUseCase tokenRefreshUseCase;


    public TokenRefreshController(TokenRefreshUseCase tokenRefreshUseCase) {
        this.tokenRefreshUseCase = tokenRefreshUseCase;
    }

    @PostMapping("/refresh")
    public TokenRefreshResponseDTO refreshToken(
            @RequestHeader("Authorization") String accessToken,
            @Valid @RequestBody TokenRefreshRequestDTO request
    ) {
        // "Bearer " prefix 제거
        String resolvedAccessToken = accessToken.startsWith("Bearer ") ? accessToken.substring(7) : accessToken;
        var responseDomain = tokenRefreshUseCase.refreshAccessToken(resolvedAccessToken, request.refreshToken());
        return TokenRefreshResponseDTO.from(responseDomain);
    }
}
