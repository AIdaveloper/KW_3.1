package com.mirea.sweetshops.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.mirea.sweetshops.models.User;
import com.mirea.sweetshops.services.TagService;
import com.mirea.sweetshops.services.UserService;

import java.util.HashSet;
import java.util.Set;

@Controller
public class LoginController {

    @Autowired
    private TagService tagService;

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


    @GetMapping("/login")
    public String login(Model model, Authentication authentication) {
        model.addAttribute("title", "Форма входа");
        model.addAttribute("userRole", getUserRole(authentication));
//        model.addAttribute("types", tagService.getAllTypes());
        return "login";
    }
}
