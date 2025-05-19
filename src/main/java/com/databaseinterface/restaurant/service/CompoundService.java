package com.databaseinterface.restaurant.service;

import com.databaseinterface.restaurant.model.Compound;
import com.databaseinterface.restaurant.model.Dish;
import com.databaseinterface.restaurant.model.Product;
import com.databaseinterface.restaurant.repository.CompoundRepository;
import com.databaseinterface.restaurant.service.DishService;
import com.databaseinterface.restaurant.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class CompoundService {

    private final CompoundRepository compoundRepository;
    private final ProductService productService;
    private final DishService dishService;

    @Autowired
    public CompoundService(CompoundRepository compoundRepository,
                           ProductService productService,
                           DishService dishService) {
        this.compoundRepository = compoundRepository;
        this.productService = productService;
        this.dishService = dishService;
    }

    public Page<Compound> findCompounds(int page, int size) {
        Page<Compound> compounds = compoundRepository.findAll(PageRequest.of(page, size));

        for (Compound c : compounds.getContent()) {
            // Подгружаем название продукта
            Product product = productService.findById(c.getProductId());
            if (product != null) {
                c.setProductName(product.getName());  // предполагается метод getName()
            } else {
                c.setProductName("Не найдено");
            }

            // Подгружаем название блюда
            Dish dish = dishService.findById(c.getDishId());
            if (dish != null) {
                c.setDishName(dish.getName());  // getName() у тебя в Dish — возвращает dishName
            } else {
                c.setDishName("Не найдено");
            }
        }

        return compounds;
    }

    public Compound findById(int id) {
        return compoundRepository.findById(id).orElse(null);
    }

    public void save(Compound compound) {
        compoundRepository.save(compound);
    }

    public void deleteById(int id) {
        compoundRepository.deleteById(id);
    }
}
