package com.example.project.service;

import com.example.project.domain.User;
import com.example.project.dto.UserDto;
import com.example.project.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    public void save(UserDto userDto) {
        User user = new User(
                userDto.getEmail(),
                userDto.getPw(),
                userDto.getUserName(),
                userDto.getPhoneNumber(),
                userDto.getAddress(),
                userDto.getRole()
        );
        userRepository.save(user);
    }
}
