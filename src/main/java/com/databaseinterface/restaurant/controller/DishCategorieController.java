package com.databaseinterface.restaurant.controller;

import com.databaseinterface.restaurant.repository.DishCategorieRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/dishCategory")
public class DishCategorieController {
    private final DishCategorieRepository dishCategorieRepository;

    public DishCategorieController (DishCategorieRepository dishCategorieRepository){
        this.dishCategorieRepository = dishCategorieRepository;
    }
    @GetMapping
    public String listDishCategorie(Model model) {
        model.addAttribute("dishCategory", dishCategorieRepository.findAll());
        return "admin/dishCategory";
    }
}