package com.example.timesnap.web.user;

import com.example.timesnap.domain.user.User;
import com.example.timesnap.domain.user.UserRepository;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostMapping("/join")
    public ResponseEntity<String> join(@ModelAttribute User user) {
        log.info("loginUser 정보 : {}", user.getUsername());

        //비밀번호 암호화
        String encodePassword = bCryptPasswordEncoder.encode(user.getPassword());
        user = User.builder()
                .username(user.getUsername())
                .password(encodePassword)
                .email(user.getEmail())
                .nickname(user.getNickname())
                .isHidden(user.getIsHidden())
                .role("ROLE_USER")
                .build();

        //회원정보 저장후 회원시퀀스 가져오기
        userRepository.save(user);

        //유저테이블에 저장되었다면 로그인창으로 이동
        return ResponseEntity
                .status(HttpStatus.MOVED_PERMANENTLY) //HttpStatus.MOVED_PERMANENTLY : 요청한 리소스가 헤더에서 제공한 URl로 이동되었음을 나타내는 상태값
                .header(HttpHeaders.LOCATION, "/home").build();
    }
}
