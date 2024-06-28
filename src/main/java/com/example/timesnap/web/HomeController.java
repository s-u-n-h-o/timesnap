package com.example.timesnap.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@Slf4j
public class HomeController {

    @GetMapping(value = "/")
    public String index() {return "index";}

    @GetMapping("/login")
    public String loginForm() {return "loginForm";}

    @GetMapping("/join")
    public String joinForm(){return "joinForm";}

    @GetMapping("/home")
    public String home(){return "home";}

    @GetMapping("/admin")
    public @ResponseBody String admin(){return "관리자만 들어올수있어요! 당신은 관리자군요";}

    @GetMapping("/user")
    public @ResponseBody String user(){return "회원과 관리자 모두 들어올수있어요! 웰컴";}

}
