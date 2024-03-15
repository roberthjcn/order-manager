package com.roberth.ordermanager.domain.client.repository;


import com.roberth.ordermanager.domain.client.entity.Client;
import com.roberth.ordermanager.generic.repository.GenericRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends GenericRepository<Client, Long>{
}
