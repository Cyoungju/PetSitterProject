package com.example.project.controller;

import com.example.project.domain.User;
import com.example.project.dto.UserDto;
import com.example.project.repository.UserRepository;
import com.example.project.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public String regMember(UserDto userDto){
        userService.save(userDto);
        return "성공!!!!";
    }

}
