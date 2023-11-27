package com.exam.springilmiofotoalbum.controller;

import com.exam.springilmiofotoalbum.model.User;
import com.exam.springilmiofotoalbum.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("/")
public class HomeController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public String home(Model model, Authentication auth) {
        Optional<User> user = userRepository.findByEmail(auth.getName());
        model.addAttribute("user", user.get());
        return "/home";
    }
}
