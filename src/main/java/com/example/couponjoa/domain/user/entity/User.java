package com.example.couponjoa.domain.user.entity;

import com.example.couponjoa.common.base.BaseEntity;
import com.example.couponjoa.domain.user.enums.UserRole;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


@Entity
@Getter
@Table(name = "users")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String email;

    private String password;

    private String username;

    private String imageUrl;

    @Enumerated(EnumType.STRING)
    private UserRole userRole;

    private Boolean isDeleted;

    private User(String email, String password, String username, UserRole userRole) {
        this.email = email;
        this.password = password;
        this.username = username;
        this.userRole = userRole;
        this.isDeleted = false;
    }

    public static User of(String email, String password, String username, UserRole userRole) {
        return new User(email, password, username, userRole);
    }

    public void updatePassword(String newPassword) {
        this.password = newPassword;
    }
}
