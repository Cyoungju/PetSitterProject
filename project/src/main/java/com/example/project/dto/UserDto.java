package com.example.project.dto;

import com.example.project.domain.User;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Collections;


public class UserDto {

    @Setter
    @Getter
    public static class UserRes {
        private Long id;
        @NotEmpty
        @Pattern(regexp = "^[\\w._%+-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$", message = "이메일 형식으로 작성해주세요")
        private String email;

        @NotEmpty
        @Size(min = 8, max = 20, message = "8자 이상 20자 이내로 작성 가능합니다.")
        @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[@#$%^&+=!~`<>,./?;:'\"\\[\\]{}\\\\()|_-])\\S*$", message = "영문, 숫자, 특수문자가 포함되어야하고 공백이 포함될 수 없습니다.")
        private String pw;

        private String userName;

        private String phoneNumber;

        @Pattern(regexp = "^[0-9]{10,11}$", message = "휴대폰 번호는 숫자 10~11자리만 가능합니다.")
        private String address;


        @Builder
        public User toEntity() {
            return User.builder()
                    .email(email)
                    .pw(pw)
                    .userName(userName)
                    .phoneNumber(phoneNumber)
                    .address(address)
                    .roles(Collections.singletonList("ROLE_USER"))
                    .build();
        }
    }


    @Setter
    @Getter
    public static class UserLogin {
        private String email;
        private String pw;
    }
}
