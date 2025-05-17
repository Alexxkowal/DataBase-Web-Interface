package com.databaseinterface.restaurant.controller;

import com.databaseinterface.restaurant.model.Client;
import com.databaseinterface.restaurant.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
            @RequestParam(required = false) String gender,
            Model model) {
        List<Client> clients = clientService.findClients(name, email, gender);
        model.addAttribute("clients", clients);
        return "admin/clients";
    }

    @PostMapping("/add")
    public String addClient(Client client) {
        clientRepository.save(client);  // Сохраняем клиента в базе данных
        return "redirect:/admin/clients";  // Перенаправление на страницу с клиентами
    }

    @GetMapping("/{id}/edit")
    public String editClient(@PathVariable("id") int id, Model model) {
        Client client = clientRepository.findById(id).orElse(null);
        if (client != null) {
            model.addAttribute("client", client);
            return "admin/client_edit"; // Шаблон для редактирования клиента
        } else {
            return "redirect:/admin/clients";
        }
    }

    @GetMapping("/{id}/delete")
    public String deleteClient(@PathVariable("id") int id) {
        clientRepository.deleteById(id);  // Удаляем клиента по ID
        return "redirect:/admin/clients";  // Перенаправляем обратно на список клиентов
    }

    @PostMapping("/{id}/update")
    public String updateClient(@PathVariable("id") int id, Client client) {
        client.setId(id);  // Устанавливаем ID, чтобы обновить существующего клиента
        clientRepository.save(client);  // Сохраняем изменения в базе данных
        return "redirect:/admin/clients";  // Перенаправляем на страницу с клиентами
    }

}