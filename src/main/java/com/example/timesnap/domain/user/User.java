package com.example.timesnap.domain.user;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Getter
@Setter
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

//    @Builder
//    public User(Long id, String username, String password, String email, String nickname, boolean isHidden
//                ,String role) {
//        this.id=id;
//        this.username = username;
//        this.password = password;
//        this.email = email;
//        this.nickname = nickname;
//        this.isHidden = isHidden;
//        this.role = role;
//    }
}
