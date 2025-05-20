package com.databaseinterface.restaurant.service;

import com.databaseinterface.restaurant.model.Client;
import com.databaseinterface.restaurant.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public Page<Client> findClients(String name, String email, Double minDiscount, Double maxDiscount, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return clientRepository.searchClients(name, email, minDiscount, maxDiscount, pageable);
    }
}

