package com.shop.shmakova.controllers;

import com.shop.shmakova.models.User;
import com.shop.shmakova.services.UserLogService;
import com.shop.shmakova.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.security.Principal;


/**
 * @author sh1chiro 27.03.2023
 */
@Controller
@RequiredArgsConstructor
@RequestMapping("/office")
public class OfficeController {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final UserLogService userLogService;
    @GetMapping("")
    public String office(Model model, Principal principal){
        User user = userService.getUserByPrincipal(principal);
        model.addAttribute("user", user);
        return "office";
    }

    @PostMapping("/update-info")
    public String updateUser(User user, Principal principal){
        User userBeforeUpdate = userService.getUserByPrincipal(principal);
        user.setDateOfCreated(userBeforeUpdate.getDateOfCreated());
        user.setId(userBeforeUpdate.getId());
        user.setActive(userBeforeUpdate.isActive());
        user.setRoles(userBeforeUpdate.getRoles());
        user.setPassword(userBeforeUpdate.getPassword());
        userLogService.log("Оновлено дані користувача. Користувач до: " + userBeforeUpdate.toString() + " | Користувач після: " +
                user.toString());
        userService.updateUser(user);
        return ("redirect:/office");
    }

    @PostMapping("/update-password")
    public String updatePassword(@RequestParam("oldPassword") String oldPassword,
                                 @RequestParam("newPassword") String newPassword,
                                 Principal principal, Model model){
        User user = userService.getUserByPrincipal(principal);
        if(passwordEncoder.matches(oldPassword, user.getPassword())){
            user.setPassword(passwordEncoder.encode(newPassword));
            userService.updateUser(user);
            userLogService.log("Змінено пароль користувача " + user.getEmail());
            return ("redirect:/office");
        }
        else{
            model.addAttribute("user", user);
            model.addAttribute("message", "Невірний пароль");
            return "office";
        }
    }

}
