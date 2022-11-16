package com.sphy.hotelmanagementapplication.service;

import com.sphy.hotelmanagementapplication.domain.Client;
import com.sphy.hotelmanagementapplication.repositories.ClientRepository;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Client getClientById(Long id){
        return clientRepository.findById(id).orElse(null);
    }
}
