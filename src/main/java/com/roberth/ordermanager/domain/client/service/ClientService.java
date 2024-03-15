package com.roberth.ordermanager.domain.client.service;

import com.roberth.ordermanager.domain.client.entity.Client;
import com.roberth.ordermanager.domain.client.repository.ClientRepository;
import com.roberth.ordermanager.generic.service.GenericService;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClientService extends GenericService<Client, Long> {

    @Autowired
    public ClientService(ClientRepository clientRepository) {
        super(clientRepository);
    }

    @Override
    public Client update(Long id, Client client) throws BadRequestException {
        Optional<Client> optional = repository.findById(id);
        if (optional.isEmpty()) {
            throw new BadRequestException("Does not exist a client with ID: " + id);
        }
        client.setId(id);
        return repository.save(client);
    }

    @Override
    public void delete(Long id) throws BadRequestException {
        Optional<Client> optional = repository.findById(id);
        if (optional.isEmpty()) {
            throw new BadRequestException("Does not exist a client with ID: " + id);
        }
        repository.deleteById(id);
    }

}
