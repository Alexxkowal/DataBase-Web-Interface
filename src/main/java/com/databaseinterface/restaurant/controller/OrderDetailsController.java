package com.databaseinterface.restaurant.controller;

import com.databaseinterface.restaurant.repository.OrderDetailRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/order_details")
public class OrderDetailsController {
    private final OrderDetailRepository orderDetailRepository;

    public OrderDetailsController (OrderDetailRepository orderDetailRepository){
        this.orderDetailRepository = orderDetailRepository;
    }
    @GetMapping
    public String listOrderDetails(Model model) {
        model.addAttribute("order_details", orderDetailRepository.findAll());
        return "admin/order_details";
    }
}