package com.databaseinterface.restaurant.repository;

import com.databaseinterface.restaurant.model.Compound;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompoundRepository extends JpaRepository<Compound, Integer> {
}
