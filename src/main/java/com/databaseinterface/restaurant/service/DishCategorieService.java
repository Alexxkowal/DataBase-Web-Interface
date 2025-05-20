package com.databaseinterface.restaurant.service;


import com.databaseinterface.restaurant.model.DishCategorie;
import com.databaseinterface.restaurant.repository.DishCategorieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class DishCategorieService {

    @Autowired
    private DishCategorieRepository dishCategorieRepository;

    public Page<DishCategorie> findAll(int page, int size) {
        return dishCategorieRepository.findAll(PageRequest.of(page, size));
    }

    public DishCategorie getById(int id) {
        return dishCategorieRepository.findById(id).orElse(null);
    }

    public void save(DishCategorie dishCategory) {
        dishCategorieRepository.save(dishCategory);
    }

    public void deleteById(int id) {
        dishCategorieRepository.deleteById(id);
    }
}
