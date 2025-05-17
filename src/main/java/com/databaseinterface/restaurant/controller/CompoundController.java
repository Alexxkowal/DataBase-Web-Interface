package com.databaseinterface.restaurant.controller;

import com.databaseinterface.restaurant.repository.CompoundRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/compound")
public class CompoundController {
    private final CompoundRepository compoundRepository;

    public CompoundController (CompoundRepository compoundRepository){
        this.compoundRepository = compoundRepository;
    }
    @GetMapping
    public String listCompound(Model model) {
        model.addAttribute("compound", compoundRepository.findAll());
        return "admin/compound";
    }
}