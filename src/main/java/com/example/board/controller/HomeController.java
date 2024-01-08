package com.example.board.controller;

import com.example.board.dto.MemberRequestDto;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

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