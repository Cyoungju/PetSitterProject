package com.example.project.service;

import com.example.project.domain.User;
import com.example.project.dto.UserDto;
import com.example.project.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    public String idCheck(String email) {
        Optional<User> users = userRepository.findByEmail(email);
        String msg= "";
        if(users.isPresent()){
            msg = "이미 사용중인 아이디 입니다!";
        }else {
            msg = "사용 가능합니다!";
        }

        return msg;
    }

    public User save(UserDto userDto) {
        User user = new User(
                userDto.getEmail(),
                userDto.getPw(),
                userDto.getUserName(),
                userDto.getPhoneNumber(),
                userDto.getAddress(),
                userDto.getRole()
        );
        User save = userRepository.save(user);
        return save;
    }
}
