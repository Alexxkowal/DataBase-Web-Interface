package com.databaseinterface.restaurant.service;

import com.databaseinterface.restaurant.model.Client;
import com.databaseinterface.restaurant.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public List<Client> findClients(String name, String email, String gender) {
        return clientRepository.findAll().stream()
            .filter(c -> name == null || c.getName().toLowerCase().contains(name.toLowerCase()))
            .filter(c -> email == null || c.getMail().toLowerCase().contains(email.toLowerCase()))
            .collect(Collectors.toList());
    }
}
