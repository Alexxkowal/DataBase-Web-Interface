package com.databaseinterface.restaurant.controller;

import com.databaseinterface.restaurant.repository.StaffRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("admin/staff")
public class StaffController {
    private final StaffRepository staffRepository;

    public StaffController (StaffRepository staffRepository){
        this.staffRepository = staffRepository;
    }
    @GetMapping
    public String listStaff(Model model) {
        model.addAttribute("staff", staffRepository.findAll());
        return "admin/staff";
    }
}