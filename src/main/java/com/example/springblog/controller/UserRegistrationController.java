package com.example.springblog.controller;

import com.example.springblog.dto.UserForm;
import com.example.springblog.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users/register")
@RequiredArgsConstructor
public class UserRegistrationController {
    private final UserService userService;

    @GetMapping
    public String register() {
        return "register";
    }

    @PostMapping
    public String doRegister(@Valid UserForm userForm) {
        userService.registerUser(userForm);
        return "redirect:/login";
    }
}
