package com.example.project.controller;

import com.example.project.domain.User;
import com.example.project.dto.UserDto;
import com.example.project.repository.UserRepository;
import com.example.project.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequiredArgsConstructor
@Controller
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public String regMember(UserDto userDto){
        User result = userService.save(userDto);
        String view = "";
        if (result != null){
            view = "success";
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

}
