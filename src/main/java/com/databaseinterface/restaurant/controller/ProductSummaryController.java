package com.databaseinterface.restaurant.controller;

import com.databaseinterface.restaurant.repository.ProductSummaryRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/product_summary")
public class ProductSummaryController {
    private final ProductSummaryRepository productSummaryRepository;

    public ProductSummaryController (ProductSummaryRepository productSummaryRepository){
        this.productSummaryRepository = productSummaryRepository;
    }
    @GetMapping
    public String listProductSummary(Model model) {
        model.addAttribute("product_summary", productSummaryRepository.findAll());
        return "admin/product_summary";
    }
}