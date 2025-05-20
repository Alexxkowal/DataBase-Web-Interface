package com.databaseinterface.restaurant.controller;

import com.databaseinterface.restaurant.model.Staff;
import com.databaseinterface.restaurant.repository.StaffRepository;
import com.databaseinterface.restaurant.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/staff")
public class StaffController {
    private final StaffRepository staffRepository;
    private final StaffService staffService;

    @Autowired
    public StaffController(StaffRepository staffRepository, StaffService staffService) {
        this.staffRepository = staffRepository;
        this.staffService = staffService;
    }

    @GetMapping
    public String listStaff(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String position,
            @RequestParam(required = false) Double minSalary,
            @RequestParam(required = false) Double maxSalary,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            Model model) {
        if (position != null && position.trim().isEmpty()) {
            position = null;
        }

        Page<Staff> staffPage = staffService.findStaff(name, position, minSalary, maxSalary, page, size);
        model.addAttribute("staffList", staffPage);
        model.addAttribute("totalPages", staffPage.getTotalPages());
        model.addAttribute("currentPage", page);
        model.addAttribute("name", name);
        model.addAttribute("position", position);
        model.addAttribute("minSalary", minSalary);
        model.addAttribute("maxSalary", maxSalary);
        return "admin/staff";
    }

    @PostMapping("/add")
    public String addStaff(Staff staff) {
        staffRepository.save(staff);
        return "redirect:/admin/staff";
    }

    @GetMapping("/{id}/edit")
    public String editStaff(@PathVariable("id") int id, Model model) {
        Staff staff = staffRepository.findById(id).orElse(null);
        if (staff != null) {
            model.addAttribute("staff", staff);
            return "admin/staff_edit";
        } else {
            return "redirect:/admin/staff";
        }
    }

    @GetMapping("/{id}/delete")
    public String deleteStaff(@PathVariable("id") int id) {
        staffRepository.deleteById(id);
        return "redirect:/admin/staff";
    }

    @PostMapping("/{id}/update")
    public String updateStaff(@PathVariable("id") int id, Staff staff) {
        staff.setId(id);
        staffRepository.save(staff);
        return "redirect:/admin/staff";
    }

    @PostMapping("/increase-salary")
    @Transactional
    public String increaseSalary(
            @RequestParam String position,
            @RequestParam Double percent) {
        staffService.increaseSalaryByPosition(position, percent);
        return "redirect:/admin/staff";
    }
}
