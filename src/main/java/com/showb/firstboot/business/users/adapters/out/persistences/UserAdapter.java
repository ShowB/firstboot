package com.showb.firstboot.business.users.adapters.out.persistences;

import com.showb.firstboot.business.users.applications.domains.primary.User;
import com.showb.firstboot.business.users.applications.port.out.UserPort;
import com.showb.firstboot.business.users.entites.UserEntity;
import com.showb.firstboot.business.users.repositories.UserRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserAdapter implements UserPort {
    private final UserRepository userRepository;


    public UserAdapter(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void save(User user) {
        UserEntity entity = UserEntity.from(user);
        userRepository.save(entity);
    }

    @Override
    public Optional<User> findByLoginId(String loginId) {
        return userRepository.findByLoginId(loginId)
                .map(UserEntity::toDomain);
    }
}
