package com.example.project.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserDto {

    private String email;

    private String pw;

    private String userName;

    private String phoneNumber;

    private String address;

    private String role;

}
