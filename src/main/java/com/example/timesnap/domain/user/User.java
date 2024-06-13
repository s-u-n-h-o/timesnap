package com.example.timesnap.domain.user;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.beans.factory.annotation.Value;

@Entity
@Getter
@Builder
@AllArgsConstructor
public final class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //JPA에서 Entity의 Primary key를 생성해주는 기능이다
    private Long id;

    @NotEmpty
    private String username;

    @NotNull
    private String password;

    @NotEmpty
    private String email;

    @NotEmpty
    private String nickname;

    private Boolean isHidden;

    private String role;
    public User() {

    }
}
