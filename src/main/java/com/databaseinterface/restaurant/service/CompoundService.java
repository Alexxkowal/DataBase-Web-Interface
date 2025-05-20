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

    public Page<Compound> findCompoundsFiltered(int page, int size,
                                            Integer productId,
                                            String productName,
                                            Integer dishId,
                                            String dishName,
                                            Double minQuantity,
                                            Double maxQuantity) {

    Page<Compound> compounds = compoundRepository.searchCompounds(
            productId, productName, dishId, dishName, minQuantity, maxQuantity,
            PageRequest.of(page, size));

    // подгружаем имена, если нужно (на всякий случай, если в модели productName и dishName transient)
    for (Compound c : compounds.getContent()) {
        if (c.getProductName() == null || c.getProductName().isEmpty()) {
            Product product = productService.findById(c.getProductId());
            c.setProductName(product != null ? product.getName() : "Не найдено");
        }
        if (c.getDishName() == null || c.getDishName().isEmpty()) {
            Dish dish = dishService.findById(c.getDishId());
            c.setDishName(dish != null ? dish.getName() : "Не найдено");
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
