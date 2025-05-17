package com.databaseinterface.restaurant.controller;

import com.databaseinterface.restaurant.repository.OrderRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/orders")
public class OrderController {
    private final OrderRepository orderRepository;

    public OrderController (OrderRepository orderRepository){
        this.orderRepository = orderRepository;
    }
    @GetMapping
    public String listOrder(Model model) {
        model.addAttribute("orders", orderRepository.findAll());
        return "admin/orders";
    }
}
