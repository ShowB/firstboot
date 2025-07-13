package com.showb.firstboot.business.users.adapters.in;

import com.showb.firstboot.business.users.adapters.in.dto.request.LoginRequestDTO;
import com.showb.firstboot.business.users.adapters.in.dto.response.LoginResponseDTO;
import com.showb.firstboot.business.users.applications.domains.login.LoginResponse;
import com.showb.firstboot.business.users.applications.port.in.LoginUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value = "/user/v1")
@RestController
public class LoginController {
    private final LoginUseCase loginUseCase;


    public LoginController(LoginUseCase loginUseCase) {
        this.loginUseCase = loginUseCase;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> createUser(@RequestBody LoginRequestDTO loginRequestDTO) {
        LoginResponse response = loginUseCase.login(loginRequestDTO.toDomain());
        LoginResponseDTO responseDTO = LoginResponseDTO.from(response);

        return ResponseEntity.ok()
                .body(responseDTO);
    }
}
