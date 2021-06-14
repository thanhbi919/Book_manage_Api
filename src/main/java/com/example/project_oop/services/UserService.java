package com.example.project_oop.services;

import com.example.project_oop.entity.UserEntity;
import com.example.project_oop.repository.UserRepository;
import com.example.project_oop.services.interfaces.CheckValidInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements UserDetailsService , CheckValidInput {
    @Autowired
    UserRepository userRepository;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
    public String register(UserEntity register){
        if(checkEmail(register.getEmail())==0){
            return "Email khong hop le";
        }
        if(checkPhone(register.getPhone_number())==0){
            return "so dien thoai khong hop le";
        }
        if(checkPassword(register.getPassword())==0){
            return "password qua yeu";
        }
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(register.getEmail());
        userEntity.setPhone_number(register.getPhone_number());
        userEntity.setPassword(bCryptPasswordEncoder.encode(register.getPassword()));
        userRepository.save(userEntity);
        return "success";
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findUserByEmail(s);
        if(userEntity == null){
            throw new RuntimeException("User " + s + " not found");
        }

        List<GrantedAuthority> grantList = new ArrayList<>();
        GrantedAuthority authority = new SimpleGrantedAuthority(userEntity.getRole());
        grantList.add(authority);

        UserDetails userDetails = new User(userEntity.getEmail(), userEntity.getPassword(), grantList);

        return userDetails;
    }

    @Override
    public int checkEmail(String email) {
        if(email.endsWith("email")==true){
            return 1;
        }else
        return 0;
    }

    @Override
    public int checkPassword(String password) {
        if(password.length()<8)
            return 0;
        else return 0;
    }

    @Override
    public int checkPhone(String phone) {
        if(phone.length()!=10)
        return 0;
        else return 1;
    }
}
