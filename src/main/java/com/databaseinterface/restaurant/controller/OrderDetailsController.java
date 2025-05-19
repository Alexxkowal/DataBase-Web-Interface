package com.databaseinterface.restaurant.controller;

import com.databaseinterface.restaurant.model.Dish;
import com.databaseinterface.restaurant.model.Order;
import com.databaseinterface.restaurant.model.OrderDetail;
import com.databaseinterface.restaurant.repository.DishRepository;
import com.databaseinterface.restaurant.repository.OrderDetailRepository;
import com.databaseinterface.restaurant.repository.OrderRepository;
import com.databaseinterface.restaurant.service.OrderDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin/orderdetails")
public class OrderDetailsController {

    private final OrderDetailRepository orderDetailRepository;
    private final OrderRepository orderRepository;
    private final DishRepository dishRepository;
    private final OrderDetailsService orderDetailsService;  // <-- добавь это поле


    @Autowired
    public OrderDetailsController(OrderDetailRepository orderDetailRepository,
                                  OrderRepository orderRepository,
                                  DishRepository dishRepository, OrderDetailsService orderDetailsService) {
        this.orderDetailsService = orderDetailsService;
        this.orderDetailRepository = orderDetailRepository;
        this.orderRepository = orderRepository;
        this.dishRepository = dishRepository;
    }

    // Список всех orderDetails
    @GetMapping
    public String listOrderDetails(
            @RequestParam(required = false) Integer orderId,
            @RequestParam(required = false) String dishName,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            Model model) {

        Page<OrderDetail> orderDetailsPage = orderDetailsService.findOrderDetails(orderId, dishName, page, size);

        // Передаем в модель весь объект Page, а не только контент
        model.addAttribute("orderDetails", orderDetailsPage);
        model.addAttribute("totalPages", orderDetailsPage.getTotalPages());
        model.addAttribute("currentPage", page);

        model.addAttribute("orders", orderRepository.findAll());
        model.addAttribute("dishes", dishRepository.findAll());

        return "admin/orderdetails";
    }


    // Добавление новой детали заказа
    @PostMapping("/add")
    public String addOrderDetail(OrderDetail orderDetail) {
        Optional<Order> orderOpt = orderRepository.findById(orderDetail.getOrder().getId());
        Optional<Dish> dishOpt = dishRepository.findById(orderDetail.getDish().getId());

        if (orderOpt.isPresent() && dishOpt.isPresent() && orderDetail.getQuantity() != null && orderDetail.getQuantity() > 0) {
            orderDetail.setOrder(orderOpt.get());
            orderDetail.setDish(dishOpt.get());
            orderDetailRepository.save(orderDetail);
        }

        return "redirect:/admin/orderdetails";
    }

    // Форма редактирования детали заказа
    @GetMapping("/{id}/edit")
    public String editOrderDetail(@PathVariable("id") Integer id, Model model) {
        Optional<OrderDetail> orderDetailOpt = orderDetailRepository.findById(id);

        if (orderDetailOpt.isPresent()) {
            model.addAttribute("orderDetail", orderDetailOpt.get());
            model.addAttribute("orders", orderRepository.findAll());
            model.addAttribute("dishes", dishRepository.findAll());
            return "admin/orderdetail_edit";
        } else {
            return "redirect:/admin/orderdetails";
        }
    }

    // Обновление детали заказа
    @PostMapping("/{id}/update")
    public String updateOrderDetail(@PathVariable("id") Integer id, OrderDetail updatedOrderDetail) {
        Optional<Order> orderOpt = orderRepository.findById(updatedOrderDetail.getOrder().getId());
        Optional<Dish> dishOpt = dishRepository.findById(updatedOrderDetail.getDish().getId());

        if (orderOpt.isPresent() && dishOpt.isPresent() && updatedOrderDetail.getQuantity() != null && updatedOrderDetail.getQuantity() > 0) {
            updatedOrderDetail.setId(id);
            updatedOrderDetail.setOrder(orderOpt.get());
            updatedOrderDetail.setDish(dishOpt.get());
            orderDetailRepository.save(updatedOrderDetail);
        }

        return "redirect:/admin/orderdetails";
    }

    // Удаление детали заказа
    @GetMapping("/{id}/delete")
    public String deleteOrderDetail(@PathVariable("id") Integer id) {
        orderDetailRepository.deleteById(id);
        return "redirect:/admin/orderdetails";
    }
}
