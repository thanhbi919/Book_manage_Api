package com.example.project_oop.controller;

import com.example.project_oop.entity.UserEntity;
import com.example.project_oop.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping(value = "/register")
    public String register(Model model) {
        UserEntity registerRequest = new UserEntity();
        model.addAttribute("registerRequest", registerRequest);
        return "register";
    }

    @PostMapping(value = "/register")
    public String register(Model model,
                           @ModelAttribute("registerRequest") UserEntity request) {
        String result = userService.register(request);
        if (result.equals("success")) {
            return "welcome";
        } else {
            return "register";
        }
    }
}
