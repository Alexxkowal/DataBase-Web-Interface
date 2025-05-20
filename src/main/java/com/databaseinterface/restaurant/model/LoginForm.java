package com.databaseinterface.restaurant.model;


public class LoginForm {
    private String role;
    private String adminPassword;
    private String email;
    private String phone; // если нужна регистрация — можно добавить

    // геттеры и сеттеры
    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public String getAdminPassword() { return adminPassword; }
    public void setAdminPassword(String adminPassword) { this.adminPassword = adminPassword; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
}
