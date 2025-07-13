package com.showb.firstboot.business.users.adapters.in;

import com.showb.firstboot.business.users.adapters.in.dto.request.UserCreateRequestDTO;
import com.showb.firstboot.business.users.applications.port.in.UserManageUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value = "/user/v1")
@RestController
public class UserManageController {
    private final UserManageUseCase userManageUseCase;


    public UserManageController(UserManageUseCase userManageUseCase) {
        this.userManageUseCase = userManageUseCase;
    }

    @PostMapping("/create")
    public ResponseEntity<Void> createUser(@RequestBody UserCreateRequestDTO userCreateRequestDTO) {
        userManageUseCase.createUser(userCreateRequestDTO.toDomain());

        return ResponseEntity.ok()
                .build();
    }

}
