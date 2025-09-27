package com.showb.firstboot.business.users.exceptions;


import com.showb.firstboot.exceptions.ExceptionType;

public enum LoginExceptionType implements ExceptionType {
    FAILED_TO_LOGIN("로그인에 실패하였습니다. ID 혹은 비밀번호를 다시 확인해 주세요."),
    USER_NOT_FOUND("사용자를 찾을 수 없습니다."),
    INVALID_REFRESH_TOKEN("유효하지 않은 리프레시 토큰입니다."),
    REFRESH_TOKEN_NOT_FOUND("리프레시 토큰을 찾을 수 없습니다."),
    INVALID_ACCESS_TOKEN("유효하지 않은 액세스 토큰입니다.")
    ;


    private final String message;


    LoginExceptionType(String message) {
        this.message = message;
    }

    @Override
    public String getType() {
        return this.name();
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
