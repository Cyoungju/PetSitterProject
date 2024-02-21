package com.example.project.domain;

import com.example.project.core.StringArrayConverter;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name="user_tb")
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false, unique = true)
    private String email;

    @Column(length = 255, nullable = false)
    private String pw;

    @Column(length = 45, nullable = false)
    private String userName;

    @Column(length = 16)
    private String phoneNumber;

    @Column(length = 500)
    private String address;

    @Column(length = 30)
    @Convert(converter = StringArrayConverter.class)
    private List<String> roles = new ArrayList<>();



    @Builder
    public User(Long id, String email, String pw, String userName, String phoneNumber,String address, List<String> roles) {
        this.email = email;
        this.pw = pw;
        this.userName = userName;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.roles = roles;
    }
}
