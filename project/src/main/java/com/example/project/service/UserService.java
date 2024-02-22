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

    @Transactional(readOnly = true)
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

    public User findById(Long id){
        Optional<User> byId = userRepository.findById(id);

        if(byId.isPresent()){
            return byId.get();
        } else {
            return null;
        }


    }

    public User save(UserDto.UserRes userRes) {
        User save = userRepository.save(userRes.toEntity());
        return save;
    }


    public User login(UserDto.UserLogin req) {
        Optional<User> optionalUser = userRepository.findByEmail(req.getEmail());

        // loginId와 일치하는 User가 없으면 null return
        if(optionalUser.isEmpty()) {
            return null;
        }

        User user = optionalUser.get();

        // 찾아온 User의 password와 입력된 password가 다르면 null return
        if(!user.getPw().equals(req.getPw())) {
            return null;
        }

        return user;
    }


}
