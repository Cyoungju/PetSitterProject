package com.example.project.controller;

import com.example.project.domain.User;
import com.example.project.dto.UserDto;
import com.example.project.repository.UserRepository;
import com.example.project.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequiredArgsConstructor
@Controller
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public String regMember(UserDto.UserRes user, HttpSession session){
        User result = userService.save(user);
        String view = "";
        if (result != null){
            view = "success";
            session.setAttribute("userDto",user);
        }else {
            view = "fail";
        }
        return view;
    }
    @GetMapping("/idcheck")
    public @ResponseBody String idcheck(@RequestParam String email){
         String result = userService.idCheck(email);
        return result;
    }

    //로그인처리
    @PostMapping("/loginProc")
    public String loginProc(UserDto.UserLogin userLogin, HttpSession session){
        String view ="";
        User user = userService.login(userLogin);

        if(user != null){
            //성공이면
            view = "home";
            session.setAttribute("userDto",user);
            // oo님 반갑습니다!
        }else {
            view = "fail";
        }

        return view;
    }


}
