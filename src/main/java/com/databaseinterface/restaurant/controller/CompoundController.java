package com.databaseinterface.restaurant.controller;

import com.databaseinterface.restaurant.model.Compound;
import com.databaseinterface.restaurant.service.CompoundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/compounds")
public class CompoundController {

    private final CompoundService compoundService;

    @Autowired
    public CompoundController(CompoundService compoundService) {
        this.compoundService = compoundService;
    }

    @GetMapping
public String listCompounds(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size,
        @RequestParam(required = false) Integer productId,
        @RequestParam(required = false) String productName,
        @RequestParam(required = false) Integer dishId,
        @RequestParam(required = false) String dishName,
        @RequestParam(required = false) Double minQuantity,
        @RequestParam(required = false) Double maxQuantity,
        Model model) {

    Page<Compound> compounds = compoundService.findCompoundsFiltered(
        page, size, productId, productName, dishId, dishName, minQuantity, maxQuantity);

    model.addAttribute("compounds", compounds);
    model.addAttribute("totalPages", compounds.getTotalPages());
    model.addAttribute("currentPage", page);
    model.addAttribute("newCompound", new Compound());

    // для сохранения значений поиска в форме
    model.addAttribute("productId", productId);
    model.addAttribute("productName", productName);
    model.addAttribute("dishId", dishId);
    model.addAttribute("dishName", dishName);
    model.addAttribute("minQuantity", minQuantity);
    model.addAttribute("maxQuantity", maxQuantity);

    return "admin/compounds";
}

    @PostMapping("/add")
    public String addCompound(Compound compound) {
        compoundService.save(compound);
        return "redirect:/admin/compounds";
    }

    @GetMapping("/{id}/edit")
    public String editCompound(@PathVariable("id") int id, Model model) {
        Compound compound = compoundService.findById(id);
        if (compound != null) {
            model.addAttribute("compound", compound);
            return "admin/compound_edit";
        } else {
            return "redirect:/admin/compounds";
        }
    }

    @PostMapping("/{id}/update")
    public String updateCompound(@PathVariable("id") int id, Compound compound) {
        compound.setId(id);
        compoundService.save(compound);
        return "redirect:/admin/compounds";
    }

    @GetMapping("/{id}/delete")
    public String deleteCompound(@PathVariable("id") int id) {
        compoundService.deleteById(id);
        return "redirect:/admin/compounds";
    }
}
