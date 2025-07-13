package com.showb.firstboot.business.common.exceptions;


import com.showb.firstboot.exceptions.ExceptionType;

public enum CommonExceptionType implements ExceptionType {
    FAILED_TO_HASHING("해싱 처리 중 에러가 발생했습니다. {}"),
    ;


    private final String message;


    CommonExceptionType(String message) {
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
