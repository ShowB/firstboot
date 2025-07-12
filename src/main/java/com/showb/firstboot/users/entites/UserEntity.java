package com.showb.firstboot.users.entites;

import com.showb.firstboot.common.entities.AuditingEntity;
import com.showb.firstboot.users.applications.domains.primary.User;
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
    private String status;

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
            String status,
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

    public static UserEntity from(User user) {
        return UserEntity.builder()
                .id(user.id())
                .companyId(user.companyId())
                .name(user.name())
                .loginId(user.loginId())
                .password(user.password())
                .status(user.status().name())
                .failCount(user.failCount())
                .pwInit(user.pwInit())
                .lastAccessedAt(user.lastAccessedAt())
                .lastPwChangedAt(user.lastPwChangedAt())
                .memo(user.memo())
                .del(user.del())
                .token(user.token())
                .lastActionAt(user.lastActionAt())
                .createdBy(user.createdBy())
                .createdAt(user.createdDate())
                .updatedBy(user.updatedBy())
                .updatedAt(user.updatedDate())
                .build();
    }
}
