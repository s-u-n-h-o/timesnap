package com.example.timesnap.web.user;

import com.example.timesnap.domain.user.User;
import com.example.timesnap.domain.user.UserRepository;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.connector.Response;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostMapping("/join")
    public ResponseEntity<String> join(@ModelAttribute @Valid User user, HttpServletResponse response) {
        log.info("loginUser 정보 : {}", user.getUsername());

        //비밀번호 암호화
        String encodePassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encodePassword);

        //로그인후 인가를 위해서 해당 회원의 구분값 추가
        user.setRole("USER");

        //유저테이블에 저장
        userRepository.save(user);

        //유저테이블에 저장되었다면 로그인창으로 이동
        return ResponseEntity.status(HttpStatus.MOVED_PERMANENTLY) //HttpStatus.MOVED_PERMANENTLY : 요청한 리소스가 헤더에서 제공한 URl로 이동되었음을 나타내는 상태값
                .header(HttpHeaders.LOCATION, "/login").build();
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(User user) {
        //로그인시 회원정보가 있다면 UserDetails에 객체를 생성한다
        //Security Session 에 Authentication 안에 UserDetails가 존재해야한다


        return ResponseEntity.ok("로그인성공");
    }
}
