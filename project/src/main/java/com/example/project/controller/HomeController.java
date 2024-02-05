package com.example.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home (){
        return "home";
    }

    // 회원가입
    @GetMapping("/register")
    public String register(){
        return "register";
    }

    // 로그인
    @GetMapping("/login")
    public String login(){
        return "login";
    }
}
