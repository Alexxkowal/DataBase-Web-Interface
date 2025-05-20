package com.databaseinterface.restaurant.service;


import com.databaseinterface.restaurant.model.TableModel;
import com.databaseinterface.restaurant.repository.TableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

@Service
public class TableService {

    private final TableRepository tableRepository;

    @Autowired
    public TableService(TableRepository tableRepository) {
        this.tableRepository = tableRepository;
    }

    public Page<TableModel> findTables(String zone, Integer minCapacity, Integer maxCapacity, int page, int size) {
        if (zone != null && zone.trim().isEmpty()) {
            zone = null;
        }

        Pageable pageable = PageRequest.of(page, size, Sort.by("id").ascending());
        return tableRepository.findTables(zone, minCapacity, maxCapacity, pageable);
    }
}

