package com.databaseinterface.restaurant.controller;

import com.databaseinterface.restaurant.model.CompoundView;
import com.databaseinterface.restaurant.repository.CompoundViewRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.LinkedHashMap;

@Controller
@RequestMapping("/client")
public class MenuController {

    private final CompoundViewRepository compoundViewRepository;

    public MenuController(CompoundViewRepository compoundViewRepository) {
        this.compoundViewRepository = compoundViewRepository;
    }

    @GetMapping("/menu")
public String showMenu(
        @RequestParam(value = "filter", required = false) String filter,
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size,
        Model model) {

    Page<CompoundView> menuPage;
    if (filter != null && !filter.isBlank()) {
        menuPage = compoundViewRepository.findByDishNameContainingIgnoreCase(filter, PageRequest.of(page, size));
    } else {
        menuPage = compoundViewRepository.findAll(PageRequest.of(page, size));
    }

    // Группировка по dishName
    Map<String, List<CompoundView>> groupedMenu = menuPage.getContent().stream()
            .collect(Collectors.groupingBy(CompoundView::getDishName, LinkedHashMap::new, Collectors.toList()));

    model.addAttribute("menuGrouped", groupedMenu);
    model.addAttribute("filter", filter);
    model.addAttribute("currentPage", page);
    model.addAttribute("hasNext", menuPage.hasNext());
    model.addAttribute("hasPrevious", menuPage.hasPrevious());

    return "client/menu";
}

}
