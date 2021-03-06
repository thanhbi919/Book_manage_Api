package com.example.project_oop.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter @Setter
@Entity
@Table(name = "user")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "email")
    private String email;

    @Column(name = "phone_number")
    private String phone_number;

    @Column(name = "password")
    private String password;

    @Column(name = "role")
    private String role;

    @Column(name = "expired_time")
    private Timestamp expiredTime;

    @Column(name = "token")
    private String token;


}
