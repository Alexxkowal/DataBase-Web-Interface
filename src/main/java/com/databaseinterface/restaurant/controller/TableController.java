package com.databaseinterface.restaurant.controller;

import com.databaseinterface.restaurant.repository.TableRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/tables")
public class TableController {
    private final TableRepository tableRepository;

    public TableController (TableRepository tableRepository){
        this.tableRepository = tableRepository;
    }
    @GetMapping
    public String listTables(Model model) {
        model.addAttribute("tables", tableRepository.findAll());
        return "admin/tables";
    }
}