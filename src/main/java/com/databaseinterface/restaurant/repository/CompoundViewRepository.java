package com.databaseinterface.restaurant.repository;


import com.databaseinterface.restaurant.model.CompoundView;

import com.databaseinterface.restaurant.model.CompoundViewId;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CompoundViewRepository extends JpaRepository<CompoundView, CompoundViewId> {
    Page<CompoundView> findByDishNameContainingIgnoreCase(String dishName, Pageable pageable);
    Page<CompoundView> findAll(Pageable pageable);
}