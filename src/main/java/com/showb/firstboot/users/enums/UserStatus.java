package com.showb.firstboot.users.enums;

import lombok.Getter;

@Getter
public enum UserStatus {
    NORMAL("정상"),
    STOP("중지"),
    LOCK("잠김"),
    DEL("삭제"),
    ;

    private final String desc;


    UserStatus(String desc) {
        this.desc = desc;
    }
}
