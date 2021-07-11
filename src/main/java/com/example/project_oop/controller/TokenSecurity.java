package com.example.project_oop.controller;

import com.example.project_oop.controller.request.LoginRequest;
import com.example.project_oop.entity.UserEntity;
import com.example.project_oop.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.UUID;

@RestController
@RequestMapping(value = "/security")
public class TokenSecurity {
    @Autowired
    UserRepository userRepository;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    private static final Integer expiredTime = 3 * 60;


    private static final Logger logger = LoggerFactory.getLogger(TokenSecurity.class);

    @PostMapping(value = "/login")
    public String login(@RequestBody LoginRequest loginRequest) {
        UserEntity userEntity = userRepository.findUserByEmail(loginRequest.getEmail());
        if (userEntity == null) {
            logger.info("Email {} not found", loginRequest.getEmail());
            throw new RuntimeException("Not found");
        }
        Boolean check = bCryptPasswordEncoder.matches(loginRequest.getPassword(), userEntity.getPassword());
        if (!check) {
            logger.info("Password {} not match email {} ", loginRequest.getEmail(), loginRequest.getPassword());
            throw new RuntimeException("Login error");
        }
        String token = UUID.randomUUID().toString();
        Timestamp expired = new Timestamp(System.currentTimeMillis() + expiredTime * 1000);
        userEntity.setToken(token);
        userEntity.setExpiredTime(expired);
        userRepository.save(userEntity);
        return token;
    }

    @GetMapping(value = "/userInfo")
    public UserEntity getUserInfo(@RequestParam(name = "token") String token) {
        UserEntity userEntity = userRepository.findUserByToken(token);
        if (userEntity == null) {
            throw new RuntimeException("Token khong hop le");
        }
        Timestamp now = new Timestamp(System.currentTimeMillis());
        if (userEntity.getExpiredTime().before(now)) {
            throw new RuntimeException("Token het han");
        }
        return userEntity;
    }
}
