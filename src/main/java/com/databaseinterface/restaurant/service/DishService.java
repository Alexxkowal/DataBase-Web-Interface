package com.databaseinterface.restaurant.service;

import com.databaseinterface.restaurant.model.Dish;
import com.databaseinterface.restaurant.repository.DishRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DishService {

    @Autowired
    private DishRepository dishRepository;

    public Page<Dish> findDishes(String name, Double minPrice, Double maxPrice, Integer category, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return dishRepository.searchDishes(name, minPrice, maxPrice, category, pageable);
    }
    public Dish findById(int id) {
        Optional<Dish> dish = dishRepository.findById(id);
        return dish.orElse(null);
    }
}
