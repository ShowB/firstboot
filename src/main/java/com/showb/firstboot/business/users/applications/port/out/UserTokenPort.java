package com.showb.firstboot.business.users.applications.port.out;

import com.showb.firstboot.business.users.applications.domains.primary.UserToken;

import java.util.Optional;

public interface UserTokenPort {
    Optional<UserToken> findByUserId(long userId);
    void saveUserToken(UserToken userToken);
}
