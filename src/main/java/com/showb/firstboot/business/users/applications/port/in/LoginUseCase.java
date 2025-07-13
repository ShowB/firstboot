package com.showb.firstboot.business.users.applications.port.in;

import com.showb.firstboot.business.users.applications.domains.login.LoginRequest;
import com.showb.firstboot.business.users.applications.domains.login.LoginResponse;

public interface LoginUseCase {
    LoginResponse login(LoginRequest loginRequest);
}
