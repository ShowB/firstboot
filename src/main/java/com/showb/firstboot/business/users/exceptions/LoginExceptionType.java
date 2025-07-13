package com.showb.firstboot.business.users.exceptions;


import com.showb.firstboot.exceptions.ExceptionType;

public enum LoginExceptionType implements ExceptionType {
    FAILED_TO_LOGIN("로그인에 실패하였습니다. ID 혹은 비밀번호를 다시 확인해 주세요."),
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
