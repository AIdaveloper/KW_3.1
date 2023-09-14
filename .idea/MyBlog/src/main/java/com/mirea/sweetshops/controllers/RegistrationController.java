package com.mirea.sweetshops.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import com.mirea.sweetshops.models.User;
import com.mirea.sweetshops.services.UserService;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

@Controller
public class RegistrationController {

    @Autowired
    private UserService userService;

    private Set<String> getUserRole(Authentication authentication) {
        if (authentication == null) {
            Set<String> roles = new HashSet<>();
            roles.add("GUEST");
            return roles;
        }
        else{
            Set<String> roles = new HashSet<>();
            ((User) userService.loadUserByUsername(authentication.getName())).getRoles().forEach(iter->{
                roles.add(iter.getName());
            });
            return roles;
        }
    }

    @GetMapping("/signup")
    public String registration(Model model, Authentication authentication) {
        System.out.println(model);
        model.addAttribute("userRole", getUserRole(authentication));
        model.addAttribute("userForm", new User());

        return "signup";
    }

    @PostMapping("/signup")
    public String registration2(@ModelAttribute("userForm") @Valid User userForm, BindingResult bindingResult, Model model) {

        System.out.println(userForm);
        if (bindingResult.hasErrors()) {
            return "signup";
        }
        if (!userService.saveUser(userForm, 2L)){
            model.addAttribute("usernameError", "Пользователь с таким именем уже существует");
            return "signup";
        }

        return "redirect:/";
    }

//    @PostMapping("/login")
//    public String login(@ModelAttribute("userForm") @Valid User userForm) {
//
//        userService.
//
//        return "redirect:/";
//    }


//    @GetMapping("/login")
//    public String login(Model model, String error, String logout) {
//        if (error != null)
//            model.addAttribute("error", "Your username and password is invalid.");
//        if (logout != null)
//            model.addAttribute("message", "You have been logged out successfully.");
//        return "login";
//    }
}
