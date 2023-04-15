package com.shop.shmakova.controllers;

import com.shop.shmakova.models.User;
import com.shop.shmakova.services.UserLogService;
import com.shop.shmakova.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author sh1chiro 20.03.2023
 */
@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final UserLogService userLogService;
    @GetMapping("/login")
    public String login(){
        return "login";
    }
    @GetMapping("/registration")
    public String registration(){
        return "registration";
    }
    @PostMapping("/registration")
    public String createUser(User user, Model model){
        if(userService.createUser(user)){
            userLogService.log("Створено новий обліковий запис " + user.toString());
            return ("redirect:/login");
        }
        else{
            model.addAttribute("errorMessage", "Користувач з email: " + user.getEmail() + " вже існує.");
            return "registration";
        }
    }
}
