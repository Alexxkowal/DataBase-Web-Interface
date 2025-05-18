package com.databaseinterface.restaurant.controller;

import com.databaseinterface.restaurant.model.Client;
import com.databaseinterface.restaurant.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import com.databaseinterface.restaurant.service.*;

@Controller
@RequestMapping("/admin/clients")
public class ClientController {
    private final ClientRepository clientRepository;
    private final ClientService clientService;

    @Autowired
    public ClientController(ClientRepository clientRepository, ClientService clientService) {
        this.clientRepository = clientRepository;
        this.clientService = clientService;
    }

    @GetMapping
    public String listClients(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) Double minDiscount,
            @RequestParam(required = false) Double maxDiscount,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            Model model) {
        Page<Client> clients = clientService.findClients(name, email, minDiscount, maxDiscount, page, size);
        model.addAttribute("clients", clients);
        model.addAttribute("totalPages", clients.getTotalPages());
    model.addAttribute("currentPage", page);
        return "admin/clients";
    }

    @PostMapping("/add")
    public String addClient(Client client) {
        clientRepository.save(client);
        return "redirect:/admin/clients";
    }

    @GetMapping("/{id}/edit")
    public String editClient(@PathVariable("id") int id, Model model) {
        Client client = clientRepository.findById(id).orElse(null);
        if (client != null) {
            model.addAttribute("client", client);
            return "admin/client_edit";
        } else {
            return "redirect:/admin/clients";
        }
    }

    @GetMapping("/{id}/delete")
    public String deleteClient(@PathVariable("id") int id) {
        clientRepository.deleteById(id);
        return "redirect:/admin/clients";
    }

    @PostMapping("/{id}/update")
    public String updateClient(@PathVariable("id") int id, Client client) {
        client.setId(id);  // Устанавливаем ID, чтобы обновить существующего клиента
        clientRepository.save(client);  // Сохраняем изменения в базе данных
        return "redirect:/admin/clients";  // Перенаправляем на страницу с клиентами
    }

}