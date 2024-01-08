package com.example.board.controller;

import com.example.board.dto.MemberRequestDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String index()
    {
        return "index";
    }

    @GetMapping ("/auth/signup")
    public String createSignUp() {
        System.out.println("Here ");
        return "auth/signup";
    }

    @GetMapping ("/auth/login")
    public String createLogin() {
        return "auth/login";
    }

}
