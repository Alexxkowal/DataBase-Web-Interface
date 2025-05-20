package com.databaseinterface.restaurant.repository;

import com.databaseinterface.restaurant.model.TableModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TableRepository extends JpaRepository<TableModel, Integer> {

    @Query("SELECT t FROM TableModel t " +
            "WHERE (:zone IS NULL OR t.zone LIKE %:zone%) " +
            "AND (:minCapacity IS NULL OR t.capacity >= :minCapacity) " +
            "AND (:maxCapacity IS NULL OR t.capacity <= :maxCapacity)")
    Page<TableModel> findTables(
            @Param("zone") String zone,
            @Param("minCapacity") Integer minCapacity,
            @Param("maxCapacity") Integer maxCapacity,
            Pageable pageable);
}
