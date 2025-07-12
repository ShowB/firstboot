package com.showb.firstboot.users.entites;

import com.showb.firstboot.common.entities.AuditingEntity;
import com.showb.firstboot.users.applications.domains.primary.User;
import com.showb.firstboot.users.enums.UserStatus;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "user")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserEntity extends AuditingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "company_id")
    private Long companyId;

    @Column(name = "name")
    private String name;

    @Column(name = "login_id")
    private String loginId;

    @Column(name = "password")
    private String password;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private UserStatus status;

    @Column(name = "fail_count")
    private Integer failCount;

    @Column(name = "pw_init")
    private Boolean pwInit = false;

    @Column(name = "last_accessed_at")
    private LocalDateTime lastAccessedAt;

    @Column(name = "last_pw_changed_at")
    private LocalDateTime lastPwChangedAt;

    @Column(name = "memo")
    private String memo;

    @Column(name = "del")
    private Boolean del = false;

    @Column(name = "token")
    private String token;

    @Column(name = "last_action_at")
    private LocalDateTime lastActionAt;


    @SuppressWarnings({"unused", "java:S107"})
    @Builder
    public UserEntity(
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
        super(createdBy, createdAt, updatedBy, updatedAt);
        this.id = id;
        this.companyId = companyId;
        this.name = name;
        this.loginId = loginId;
        this.password = password;
        this.status = status;
        this.failCount = failCount;
        this.pwInit = pwInit;
        this.lastAccessedAt = lastAccessedAt;
        this.lastPwChangedAt = lastPwChangedAt;
        this.memo = memo;
        this.del = del;
        this.token = token;
        this.lastActionAt = lastActionAt;
    }

    public static UserEntity from(User from) {
        return UserEntity.builder()
                .id(from.id())
                .companyId(from.companyId())
                .name(from.name())
                .loginId(from.loginId())
                .password(from.password())
                .status(from.status())
                .failCount(from.failCount())
                .pwInit(from.pwInit())
                .lastAccessedAt(from.lastAccessedAt())
                .lastPwChangedAt(from.lastPwChangedAt())
                .memo(from.memo())
                .del(from.del())
                .token(from.token())
                .lastActionAt(from.lastActionAt())
                .createdBy(from.createdBy())
                .createdAt(from.createdAt())
                .updatedBy(from.updatedBy())
                .updatedAt(from.updatedAt())
                .build();
    }

    public User toDomain() {
        return User.builder()
                .id(this.id)
                .companyId(this.companyId)
                .name(this.name)
                .loginId(this.loginId)
                .password(this.password)
                .status(this.status)
                .failCount(this.failCount)
                .pwInit(this.pwInit)
                .lastAccessedAt(this.lastAccessedAt)
                .lastPwChangedAt(this.lastPwChangedAt)
                .memo(this.memo)
                .del(this.del)
                .token(this.token)
                .lastActionAt(this.lastActionAt)
                .createdBy(super.getCreatedBy())
                .createdAt(super.getCreatedAt())
                .updatedBy(super.getUpdatedBy())
                .updatedAt(super.getUpdatedAt())
                .build();
    }
}
