package com.example.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
public class UserController {

    @PostMapping("/register")
    public String afterRegister() {
        return  "";
    }

    @GetMapping("/register")
    public String beforeRegister() {
        return  "users/register";
    }

    @PostMapping ("/login")
    public String afterLogin() {
        return  "";
    }

    @GetMapping("/login")
    public String beforeLogin() {
        return  "users/login";
    }

}

