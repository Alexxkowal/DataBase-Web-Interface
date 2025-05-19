package com.databaseinterface.restaurant.service;

import com.databaseinterface.restaurant.model.OrderDetail;
import com.databaseinterface.restaurant.repository.OrderDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

@Service
public class OrderDetailsService {
    private final OrderDetailRepository orderDetailRepository;

    // Внедрение репозитория через конструктор
    public OrderDetailsService(OrderDetailRepository orderDetailRepository) {
        this.orderDetailRepository = orderDetailRepository;
    }

    public Page<OrderDetail> findOrderDetails(Integer orderId, String dishName, int page, int size) {
    Pageable pageable = PageRequest.of(page, size);
    return orderDetailRepository.findByOrderIdAndDishId(orderId, dishName, pageable);
}

}
