package com.databaseinterface.restaurant.repository;

import com.databaseinterface.restaurant.model.TableModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TableRepository extends JpaRepository<TableModel, Integer> {
}
