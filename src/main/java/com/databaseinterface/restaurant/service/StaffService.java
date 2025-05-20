package com.databaseinterface.restaurant.service;

import com.databaseinterface.restaurant.model.Staff;
import com.databaseinterface.restaurant.repository.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StaffService {

    @Autowired
    private StaffRepository staffRepository;

    public Page<Staff> findStaff(String name, String position, Double minSalary, Double maxSalary, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return staffRepository.searchStaff(name, position, minSalary, maxSalary, pageable);
    }
    @Transactional
    public void increaseSalaryByPosition(String position, Double percent) {
    staffRepository.increaseSalaryByPosition(position, percent);
}
}
