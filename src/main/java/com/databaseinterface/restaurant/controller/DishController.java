package com.databaseinterface.restaurant.controller;

import com.databaseinterface.restaurant.model.Dish;
import com.databaseinterface.restaurant.repository.DishRepository;
import com.databaseinterface.restaurant.service.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/dishes")
public class DishController {

    private final DishRepository dishRepository;
    private final DishService dishService;

    @Autowired
    public DishController(DishRepository dishRepository, DishService dishService) {
        this.dishRepository = dishRepository;
        this.dishService = dishService;
    }

    @GetMapping
    public String listDishes(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Double minPrice,
            @RequestParam(required = false) Double maxPrice,
            @RequestParam(required = false) Integer category,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            Model model) {

        Page<Dish> dishes = dishService.findDishes(name, minPrice, maxPrice, category, page, size);

        model.addAttribute("dishes", dishes);
        model.addAttribute("totalPages", dishes.getTotalPages());
        model.addAttribute("currentPage", page);
        return "admin/dishes";
    }

    @PostMapping("/add")
    public String addDish(Dish dish) {
        dishRepository.save(dish);
        return "redirect:/admin/dishes";
    }

    @GetMapping("/{id}/edit")
    public String editDish(@PathVariable("id") int id, Model model) {
        Dish dish = dishRepository.findById(id).orElse(null);
        if (dish != null) {
            model.addAttribute("dish", dish);
            return "admin/dish_edit";
        } else {
            return "redirect:/admin/dishes";
        }
    }

    @GetMapping("/{id}/delete")
    public String deleteDish(@PathVariable("id") int id) {
        dishRepository.deleteById(id);
        return "redirect:/admin/dishes";
    }

    @PostMapping("/{id}/update")
    public String updateDish(@PathVariable("id") int id, Dish dish) {
        dish.setId(id);
        dishRepository.save(dish);
        return "redirect:/admin/dishes";
    }
}
