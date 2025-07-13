package com.showb.firstboot.business.users.applications.domains.primary;

import com.showb.firstboot.business.users.enums.UserStatus;
import com.showb.firstboot.utils.encrypt.HashingUtils;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record User(
        Long id,
        Long companyId,
        String name,
        String loginId,
        String password,
        UserStatus status,
        Integer failCount,
        Boolean pwInit,
        LocalDateTime lastAccessedAt,
        LocalDateTime lastPwChangedAt,
        String memo,
        Boolean del,
        String token,
        LocalDateTime lastActionAt,
        String createdBy,
        LocalDateTime createdAt,
        String updatedBy,
        LocalDateTime updatedAt
) {
    public static User create(String name, String loginId, String plainPassword) {
        return User.builder()
                .name(name)
                .loginId(loginId)
                .password(HashingUtils.hashingSHA512(plainPassword))
                .status(UserStatus.NORMAL)
                .failCount(0)
                .pwInit(false)
                .lastAccessedAt(LocalDateTime.now())
                .lastPwChangedAt(LocalDateTime.now())
                .del(false)
                .lastActionAt(LocalDateTime.now())
                .build();
    }

    public boolean isLocked() {
        return this.status == UserStatus.LOCK;
    }

    public boolean isStopped() {
        return this.status == UserStatus.STOP;
    }
}
