package com.databaseinterface.restaurant.service;

import com.databaseinterface.restaurant.model.Order;
import com.databaseinterface.restaurant.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    public Page<Order> findOrders(Integer orderId, String status, Double minPrice, Double maxPrice, int page, int size) {
    Pageable pageable = PageRequest.of(page, size);
    return orderRepository.searchOrders(orderId, status, minPrice, maxPrice, pageable);
}}

