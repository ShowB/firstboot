package com.showb.firstboot.users.adapters.out.persistences;

import com.showb.firstboot.users.applications.domains.primary.User;
import com.showb.firstboot.users.applications.port.out.UserManagePort;
import com.showb.firstboot.users.entites.UserEntity;
import com.showb.firstboot.users.repositories.UserRepository;
import org.springframework.stereotype.Component;

@Component
public class UserManageAdapter implements UserManagePort {
    private final UserRepository userRepository;


    public UserManageAdapter(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void createUser(User user) {
        UserEntity entity = UserEntity.from(user);
        userRepository.save(entity);
    }
}
