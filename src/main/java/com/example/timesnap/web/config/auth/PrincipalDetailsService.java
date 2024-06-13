package com.example.timesnap.web.config.auth;

import com.example.timesnap.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.example.timesnap.domain.user.User;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class PrincipalDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    //http로 로그인 요청
    //로그인 인증요청이라면 (/login) usernamePasswordAuthenticationFilter가 요청을 가로채 전달된 username 과 password 파라미터를 이용해
    //usernaemPasswordAuthenticationToken 인증 객체를 생성한다

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("userDetails 시작");

        User userEntity = userRepository.findByUsername(username);
        Optional<User> userOptional = Optional.ofNullable(userEntity); //Optional.ofNullable<T> : null일수도 있는 값을 optional로 감쌀때 사용하는 함수로
                                                                       // 만일 값이 null일 경우 비어있는 Optional객체가 반환된다

        return userOptional.map(PrincipalDetails::new).orElse(null);
    }
}
