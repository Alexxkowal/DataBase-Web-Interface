package com.databaseinterface.restaurant.controller;

import com.databaseinterface.restaurant.model.Client;
import com.databaseinterface.restaurant.model.LoginForm;
import com.databaseinterface.restaurant.model.RegisterForm;
import com.databaseinterface.restaurant.repository.ClientRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {

    private final ClientRepository clientRepository;

    public LoginController(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @GetMapping("/login")
    public String showLoginPage(Model model) {
        model.addAttribute("role", "client");
        model.addAttribute("loginForm", new LoginForm());
        model.addAttribute("registerForm", new RegisterForm());
        return "login";
    }

    @PostMapping("/login")
    public String handleLogin(@ModelAttribute("loginForm") LoginForm loginForm, Model model) {
        String role = loginForm.getRole();
        System.out.println("ROLE: " + loginForm.getRole());
System.out.println("PASSWORD: " + loginForm.getAdminPassword());

        if ("admin".equals(role)) {
            if ("admin123".equals(loginForm.getAdminPassword())) {
                return "redirect:/admin/adminMain";
            } else {
                model.addAttribute("error", "Неверный пароль администратора");
                model.addAttribute("registerForm", new RegisterForm());
                return "login";
            }
        } else if ("client".equals(role)) {
            String email = loginForm.getEmail();
            if (email == null || email.isBlank()) {
                model.addAttribute("error", "Пожалуйста, введите почту для входа");
                model.addAttribute("registerForm", new RegisterForm());
                return "login";
            }
            Client client = clientRepository.findByMail(email).orElse(null);
            if (client != null) {
                return "redirect:/client/clientMain";
            } else {
                model.addAttribute("error", "Клиент с такой почтой не найден. Зарегистрируйтесь.");
                model.addAttribute("registerForm", new RegisterForm());
                return "login";
            }
        }
        model.addAttribute("error", "Выберите корректную роль");
        model.addAttribute("registerForm", new RegisterForm());
        return "login";
    }

    @PostMapping("/register")
    public String handleRegister(@ModelAttribute("registerForm") RegisterForm registerForm, Model model) {
        String email = registerForm.getEmail();
        String phone = registerForm.getPhone();
        String name = registerForm.getName();

        if (email == null || email.isBlank() || phone == null || phone.isBlank() || name.isBlank()) {
            model.addAttribute("error", "Пожалуйста, заполните все поля регистрации");
            model.addAttribute("loginForm", new LoginForm());
            return "login";
        }

        if (clientRepository.findByMail(email).isPresent()) {
            model.addAttribute("error", "Пользователь с такой почтой уже существует");
            model.addAttribute("loginForm", new LoginForm());
            return "login";
        }

        Client newClient = new Client();
        newClient.setMail(email);
        newClient.setTelephone(phone);
        newClient.setName(name);
        // Установите другие поля при необходимости

        clientRepository.save(newClient);

        model.addAttribute("message", "Регистрация прошла успешно. Теперь вы можете войти.");
        model.addAttribute("loginForm", new LoginForm());
        model.addAttribute("registerForm", new RegisterForm());
        return "login";
    }

    // Редирект с /client на /client/clientMain
    @GetMapping("/client")
    public String clientRedirect() {
        return "redirect:/client/clientMain";
    }

    @GetMapping("/client/clientMain")
    public String clientPage() {
        return "client/clientMain"; // шаблон: templates/client/clientMain.html
    }

    @GetMapping("/admin/adminMain")
    public String adminPage() {
        return "admin/adminMain"; // шаблон: templates/admin/adminMain.html
    }
}
