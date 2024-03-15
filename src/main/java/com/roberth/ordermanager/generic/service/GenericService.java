package com.roberth.ordermanager.generic.service;

import com.roberth.ordermanager.generic.repository.GenericRepository;
import org.apache.coyote.BadRequestException;

import java.util.List;
import java.util.Optional;

public abstract class GenericService<T, ID> {
    protected final GenericRepository<T, ID> repository;

    public GenericService(GenericRepository<T, ID> repository) {
        this.repository = repository;
    }

    public List<T> getAll() {
        return repository.findAll();
    }

    public Optional<T> getById(ID id) {
        return repository.findById(id);
    }

    public T save(T entity) {
        return repository.save(entity);
    }

    public T update(ID id, T entity) throws BadRequestException {
        Optional<T> optional = repository.findById(id);
        if (optional.isPresent()) {
            return repository.save(entity);
        } else {
            throw new RuntimeException("Does not exist with ID: " + id);
        }
    }

    public void delete(ID id) throws BadRequestException {
        repository.deleteById(id);
    }
}