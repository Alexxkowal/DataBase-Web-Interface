package com.databaseinterface.restaurant.service;

import com.databaseinterface.restaurant.model.Product;
import com.databaseinterface.restaurant.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Page<Product> findProducts(String name, String type, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return productRepository.searchProducts(name, type, pageable);
    }

    public void saveProduct(Product product) {
        productRepository.save(product);
    }

    public Product getProductById(int id) {
        return productRepository.findById(id).orElse(null);
    }

    public void deleteProductById(int id) {
        productRepository.deleteById(id);
    }

    public Product findById(int id) {
        return productRepository.findById(id).orElse(null);
    }
}
