package com.showb.firstboot.business.users.exceptions;


import com.showb.firstboot.exceptions.ExceptionType;

public enum UserManageExceptionType implements ExceptionType {
    DUPLICATED_LOGIN_ID("이미 사용중인 ID 입니다: {}"),
    ;


    private final String message;


    UserManageExceptionType(String message) {
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
