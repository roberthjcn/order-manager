package com.roberth.ordermanager.domain.client.controller;

import com.roberth.ordermanager.domain.client.entity.Client;
import com.roberth.ordermanager.domain.client.service.ClientService;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/clients")
public class ClientController {

    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping
    public List<Client> getAll() {
        return this.clientService.getAll();
    }

    @GetMapping("/{id}")
    public Optional<Client> getById(@PathVariable Long id) {
        return this.clientService.getById(id);
    }

    @PostMapping
    public Client create(@RequestBody Client client) {
        return this.clientService.save(client);
    }

    @PutMapping("/{id}")
    public Client updateClient(@PathVariable Long id, @RequestBody Client client) throws BadRequestException {
        return clientService.update(id, client);
    }

    @DeleteMapping("/{id}")
    public void deleteClient(@PathVariable Long id) throws BadRequestException {
        clientService.delete(id);
    }
}
