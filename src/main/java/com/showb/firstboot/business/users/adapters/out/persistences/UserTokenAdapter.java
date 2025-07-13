package com.showb.firstboot.business.users.adapters.out.persistences;

import com.showb.firstboot.business.users.applications.domains.primary.UserToken;
import com.showb.firstboot.business.users.applications.port.out.UserTokenPort;
import com.showb.firstboot.business.users.entites.UserTokenEntity;
import com.showb.firstboot.business.users.repositories.UserTokenRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserTokenAdapter implements UserTokenPort {
    private final UserTokenRepository userTokenRepository;


    public UserTokenAdapter(UserTokenRepository userTokenRepository) {
        this.userTokenRepository = userTokenRepository;
    }

    @Override
    public Optional<UserToken> findByUserId(long userId) {
        return userTokenRepository.findByUserId(userId)
                .map(UserTokenEntity::toDomain);
    }

    @Override
    public void saveUserToken(UserToken userToken) {
        UserTokenEntity entity = UserTokenEntity.from(userToken);
        userTokenRepository.save(entity);
    }
}
