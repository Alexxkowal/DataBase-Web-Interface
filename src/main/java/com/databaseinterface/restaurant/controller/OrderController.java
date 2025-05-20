package com.databaseinterface.restaurant.controller;


import com.databaseinterface.restaurant.model.Order;
import com.databaseinterface.restaurant.repository.OrderRepository;
import com.databaseinterface.restaurant.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.Page;

@Controller
@RequestMapping("/admin/orders")
public class OrderController {

    private final OrderRepository orderRepository;
    private final OrderService orderService;

    @Autowired
    public OrderController(OrderRepository orderRepository, OrderService orderService) {
        this.orderRepository = orderRepository;
        this.orderService = orderService;
    }

    @GetMapping
    public String listOrders(
            @RequestParam(required = false) Integer orderId,
            @RequestParam(required = false) String status,
            @RequestParam(required = false) Double minPrice,
            @RequestParam(required = false) Double maxPrice,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            Model model) {

        Page<Order> orders = orderService.findOrders(orderId, status, minPrice, maxPrice, page, size);
        model.addAttribute("orders", orders);
        model.addAttribute("totalPages", orders.getTotalPages());
        model.addAttribute("currentPage", page);
        return "admin/orders";
    }


    @PostMapping("/add")
    public String addOrder(Order order) {
        orderRepository.save(order);
        return "redirect:/admin/orders";
    }

    @GetMapping("/{id}/edit")
    public String editOrder(@PathVariable("id") int id, Model model) {
        Order order = orderRepository.findById(id).orElse(null);
        if (order != null) {
            model.addAttribute("order", order);
            return "admin/order_edit";
        } else {
            return "redirect:/admin/orders";
        }
    }

    @GetMapping("/{id}/delete")
    public String deleteOrder(@PathVariable("id") int id) {
        orderRepository.deleteById(id);
        return "redirect:/admin/orders";
    }

    @PostMapping("/{id}/update")
    public String updateOrder(@PathVariable("id") int id, Order order) {
        order.setId(id);
        orderRepository.save(order);
        return "redirect:/admin/orders";
    }
}

