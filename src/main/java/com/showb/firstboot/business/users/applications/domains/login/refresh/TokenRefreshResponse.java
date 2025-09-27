package com.showb.firstboot.business.users.applications.domains.login.refresh;

import lombok.Builder;

@Builder
public record TokenRefreshResponse(
        String accessToken,
        String refreshToken
) {
}
