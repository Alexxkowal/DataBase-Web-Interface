package com.databaseinterface.restaurant.controller;

import com.databaseinterface.restaurant.model.DishCategorie;
import com.databaseinterface.restaurant.service.DishCategorieService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
@Controller
@RequestMapping("/admin/dish_categories")
public class DishCategorieController {

    private final DishCategorieService dishCategorieService;

    public DishCategorieController(DishCategorieService dishCategorieService) {
        this.dishCategorieService = dishCategorieService;
    }

    @GetMapping
    public String listDishCategorie(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            Model model) {
        var categories = dishCategorieService.findAll(page, size);
        model.addAttribute("categories", categories);
        model.addAttribute("totalPages", categories.getTotalPages());
        model.addAttribute("currentPage", page);
        model.addAttribute("newCategory", new DishCategorie());
        return "admin/dish_categories";
    }

    @GetMapping("/{id}/edit")
    public String editDishCategorie(@PathVariable int id, Model model) {
        DishCategorie category = dishCategorieService.getById(id);
        if (category == null) {
            return "redirect:/admin/dish_categories";
        }
        model.addAttribute("category", category);
        return "admin/dish_category_edit";
    }

    @PostMapping("/{id}/update")
    public String updateDishCategorie(@PathVariable int id, @ModelAttribute DishCategorie category) {
        category.setId(id);
        dishCategorieService.save(category);
        return "redirect:/admin/dish_categories";
    }

    @GetMapping("/{id}/delete")
    public String deleteDishCategorie(@PathVariable int id) {
        dishCategorieService.deleteById(id);
        return "redirect:/admin/dish_categories";
    }

    @PostMapping("/add")
    public String addDishCategorie(@ModelAttribute DishCategorie newCategory) {
        dishCategorieService.save(newCategory);
        return "redirect:/admin/dish_categories";
    }
}
