package com.example.project_oop.controller;

import com.example.project_oop.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import java.security.Principal;

@Controller
public class SecurityController {

    @GetMapping(value = {"/", "/welcome"})
    public String welcome(Model model){
    model.addAttribute("title", "Welcome");
    model.addAttribute("message", "This is welcome page");
    return "welcome";
}

    @GetMapping("/userInfo")
    public String userInfo(Model model, Principal principal){
        String userName = principal.getName();
        User loginUser =(User) ((Authentication) principal).getPrincipal();
        String userInfo = loginUser.toString();
        model.addAttribute("userInfo", userInfo);
        return "userinfo";
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String adminPage(Model model, Principal principal) {

        User loginedUser = (User) ((Authentication) principal).getPrincipal();

        String userInfo = loginedUser.toString();
        model.addAttribute("userInfo", userInfo);

        return "admin";

    }
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage(Model model) {
        return "index";
    }

    @RequestMapping(value = "/logoutSuccessful", method = RequestMethod.GET)
    public String logoutSuccessfulPage(Model model) {
        model.addAttribute("title", "Logout");
        return "logout";
    }
    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public String accessDenied(Model model, Principal principal) {
        if (principal != null) {
            User loginedUser = (User) ((Authentication) principal).getPrincipal();
            String userInfo = loginedUser.toString();

            model.addAttribute("userInfo", userInfo);
            String message = "Hi " + principal.getName()
                    + "<br> You do not have permission to access this page!";
            model.addAttribute("message", message);
        }
        return "403";
    }


}
