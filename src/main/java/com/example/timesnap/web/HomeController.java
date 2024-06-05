package com.example.timesnap.web;

import com.example.timesnap.domain.user.User;
import com.example.timesnap.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.connector.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@Slf4j
public class HomeController {

    @GetMapping(value = "/")
    public String home() {return "index";}

    @GetMapping("/login")
    public String loginForm() {return "loginForm";}
    
    @GetMapping("/join")
    public String joinForm(){return "joinForm";}

}
