package com.databaseinterface.restaurant.controller;

import com.databaseinterface.restaurant.repository.ProductRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/products")
public class ProductController {
    private final ProductRepository productRepository;

    public ProductController (ProductRepository productRepository){
        this.productRepository = productRepository;
    }
    @GetMapping
    public String listProduct(Model model) {
        model.addAttribute("products", productRepository.findAll());
        return "admin/products";
    }
}
