package com.example.project.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

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

    private String role;


    public User(String email, String pw, String userName, String phoneNumber, String address, String role) {
        this.email = email;
        this.pw = pw;
        this.userName = userName;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.role = role;
    }
}
