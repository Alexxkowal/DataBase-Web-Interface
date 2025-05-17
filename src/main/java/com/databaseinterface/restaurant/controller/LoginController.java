package com.databaseinterface.restaurant.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String showLoginPage(Model model){
        model.addAttribute("role", "client");
        return "login";
    }

    @PostMapping("/login")
    public String handleLogin(@RequestParam String role,
                              @RequestParam(required = false) String adminPassword,
                              Model model) {
        if ("admin".equals(role)) {
            if ("admin123".equals(adminPassword)) {
                return "redirect:/admin/adminMain";
            } else {
                model.addAttribute("error", "Неверный пароль администратора");
                return "login";
            }
        }
        return "redirect:/client";
    }

    @GetMapping("/client")
    public String clientPage() {
        return "client"; // шаблон client.html
    }

    @GetMapping("" +
            "admin/adminMain")
    public String adminPage() {
        return "admin/adminMain"; // шаблон adminMain.html
    }
}
