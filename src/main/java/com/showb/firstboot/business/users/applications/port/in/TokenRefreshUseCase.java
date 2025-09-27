package com.showb.firstboot.business.users.applications.port.in;

import com.showb.firstboot.business.users.applications.domains.login.refresh.TokenRefreshResponse;

public interface TokenRefreshUseCase {
    TokenRefreshResponse refreshAccessToken(String accessToken, String refreshToken);
}
