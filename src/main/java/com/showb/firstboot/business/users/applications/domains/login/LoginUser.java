package com.showb.firstboot.business.users.applications.domains.login;

import com.showb.firstboot.business.users.applications.domains.primary.User;
import com.showb.firstboot.business.users.enums.UserStatus;
import com.showb.firstboot.utils.encrypt.HashingUtils;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.Objects;

@Builder(toBuilder = true)
public record LoginUser(
        Long userId,
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
        Boolean del
) {
    public static LoginUser from(User user) {
        return LoginUser.builder()
                .userId(user.id())
                .companyId(user.companyId())
                .name(user.name())
                .loginId(user.loginId())
                .password(user.password())
                .status(user.status())
                .failCount(user.failCount())
                .pwInit(user.pwInit())
                .lastAccessedAt(user.lastAccessedAt())
                .lastPwChangedAt(user.lastPwChangedAt())
                .memo(user.memo())
                .del(user.del())
                .build();
    }

    public boolean nonMatchedPassword(String inputPassword) {
        return !Objects.equals(password, HashingUtils.hashingSHA512(inputPassword));
    }

    public LoginUser login() {
        return this.toBuilder()
                .lastAccessedAt(LocalDateTime.now())
                .build();
    }

    public User toDomain() {
        return User.builder()
                .id(userId)
                .companyId(companyId)
                .name(name)
                .loginId(loginId)
                .password(password)
                .status(status)
                .failCount(failCount)
                .pwInit(pwInit)
                .lastAccessedAt(lastAccessedAt)
                .lastPwChangedAt(lastPwChangedAt)
                .memo(memo)
                .del(del)
                .build();
    }
}
