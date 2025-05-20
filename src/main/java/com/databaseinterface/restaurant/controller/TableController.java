package com.databaseinterface.restaurant.controller;

import com.databaseinterface.restaurant.model.TableModel;
import com.databaseinterface.restaurant.repository.TableRepository;
import com.databaseinterface.restaurant.service.TableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/tables")
public class TableController {

    private final TableRepository tableRepository;
    private final TableService tableService;

    @Autowired
    public TableController(TableRepository tableRepository, TableService tableService) {
        this.tableRepository = tableRepository;
        this.tableService = tableService;
    }

    @GetMapping
    public String listTables(
            @RequestParam(required = false) String zone,
            @RequestParam(required = false) Integer minCapacity,
            @RequestParam(required = false) Integer maxCapacity,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            Model model) {

        if (zone != null && zone.trim().isEmpty()) {
            zone = null;
        }

        Page<TableModel> tablesPage = tableService.findTables(zone, minCapacity, maxCapacity, page, size);

        model.addAttribute("tables", tablesPage);
        model.addAttribute("totalPages", tablesPage.getTotalPages());
        model.addAttribute("currentPage", page);

        return "admin/tables";
    }

    @PostMapping("/add")
    public String addTable(TableModel table) {
        tableRepository.save(table);
        return "redirect:/admin/tables";
    }

    @GetMapping("/{id}/edit")
    public String editTable(@PathVariable("id") int id, Model model) {
        TableModel table = tableRepository.findById(id).orElse(null);
        if (table != null) {
            model.addAttribute("table", table);
            return "admin/table_edit";
        } else {
            return "redirect:/admin/tables";
        }
    }

    @GetMapping("/{id}/delete")
    public String deleteTable(@PathVariable("id") int id) {
        tableRepository.deleteById(id);
        return "redirect:/admin/tables";
    }

    @PostMapping("/{id}/update")
    public String updateTable(@PathVariable("id") int id, TableModel table) {
        table.setId(id);
        tableRepository.save(table);
        return "redirect:/admin/tables";
    }
}
