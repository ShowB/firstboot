package com.showb.firstboot.business.users.entites;

import com.showb.firstboot.business.common.entities.AuditingEntity;
import com.showb.firstboot.business.users.applications.domains.primary.UserToken;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "user_token")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserTokenEntity extends AuditingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "access_token")
    private String accessToken;

    @Column(name = "refresh_token")
    private String refreshToken;

    @Column(name = "last_refreshed_at")
    private LocalDateTime lastRefreshedAt;


    @SuppressWarnings({"unused", "java:S107"})
    @Builder
    public UserTokenEntity(
            Long id,
            Long userId,
            String accessToken,
            String refreshToken,
            LocalDateTime lastRefreshedAt,
            String createdBy,
            LocalDateTime createdAt,
            String updatedBy,
            LocalDateTime updatedAt
    ) {
        super(createdBy, createdAt, updatedBy, updatedAt);
        this.id = id;
        this.userId = userId;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.lastRefreshedAt = lastRefreshedAt;
    }

    public static UserTokenEntity from(UserToken from) {
        return UserTokenEntity.builder()
                .id(from.id())
                .userId(from.userId())
                .accessToken(from.accessToken())
                .refreshToken(from.refreshToken())
                .lastRefreshedAt(from.lastRefreshedAt())
                .createdBy(from.createdBy())
                .createdAt(from.createdAt())
                .updatedBy(from.updatedBy())
                .updatedAt(from.updatedAt())
                .build();
    }

    public UserToken toDomain() {
        return UserToken.builder()
                .id(this.id)
                .userId(this.userId)
                .accessToken(this.accessToken)
                .refreshToken(this.refreshToken)
                .lastRefreshedAt(this.lastRefreshedAt)
                .createdBy(super.getCreatedBy())
                .createdAt(super.getCreatedAt())
                .updatedBy(super.getUpdatedBy())
                .updatedAt(super.getUpdatedAt())
                .build();
    }
}
