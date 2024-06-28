package com.example.timesnap.web.config.auth;

import com.example.timesnap.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;


@Slf4j
@Data
@RequiredArgsConstructor
public class PrincipalDetails implements UserDetails ,OAuth2User {//시큐리티 세션에는 Authentication 타입의 객체만 사용이 가능하기 때문에 두가지 버전의 로그인을 사용하기위해서 상속을 받는다

    private final User user;
    private Map<String, Object> attributes;

    public PrincipalDetails(User user, Map<String, Object> attributes) {
        this.user = user;
        this.attributes = attributes;
    }

    public Long getId(){
        return user.getId();
    }

    @Override
    public <A> A getAttribute(String name) {
        return OAuth2User.super.getAttribute(name);
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {//사용자에게 부여된 권한을 반환, 즉 해당 user를 반환하는곳
        Collection<GrantedAuthority> collect = new ArrayList<>();

        collect.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return user.getRole();
            }
        });
        return collect;
    }

    @Override
    public String getPassword() {//사용자를 인증하는데 사용되는 비밀번호를 반환
        return user.getPassword();
    }

    @Override
    public String getUsername() {//사용자를 인증하는데 사용되는 이름을 반환
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {//사용자의 계정이 만료되었는지 여부를 나타낸다
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {//사용자가 잠겨있는지 또는 잠금 해제되어있는지를 나타낸다
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {//사용자의 자격증명(비밀번호)이 만료되어있는지 여부
        return true;
    }

    @Override
    public boolean isEnabled() {//사용자가 활성화 되었는지 또는 비활성화 되었는지 여부
        return true;
    }

}
