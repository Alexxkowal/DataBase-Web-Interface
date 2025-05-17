package com.databaseinterface.restaurant.controller;

import com.databaseinterface.restaurant.repository.DishRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/dishes")
public class DishController {
    private final DishRepository dishRepository;

    public DishController (DishRepository dishRepository){
        this.dishRepository = dishRepository;
    }
    @GetMapping
    public String listDishes(Model model) {
        model.addAttribute("dishes", dishRepository.findAll());
        return "admin/dishes";
    }
}
