package com.example.timesnap.web;

import com.example.timesnap.domain.user.User;
import com.example.timesnap.domain.user.UserRepository;
import com.example.timesnap.web.config.auth.PrincipalOauth2UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.connector.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@Slf4j
public class HomeController {

    @GetMapping(value = "/")
    public String index() {return "index";}

    @GetMapping("/login")
    public String loginForm() {log.info("로그인했는데 여기로옴");return "loginForm";}

    @GetMapping("/join")
    public String joinForm(){return "joinForm";}

    @GetMapping("/home")
    public String home(){return "home";}

    @GetMapping("/admin")
    public @ResponseBody String admin(){return "관리자만 들어올수있어요! 당신은 관리자군요";}

    @GetMapping("/user")
    public @ResponseBody String user(){return "회원과 관리자 모두 들어올수있어요! 웰컴";}

}
