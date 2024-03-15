package com.roberth.ordermanager.domain.order.repository;

import com.roberth.ordermanager.domain.order.entity.Order;
import com.roberth.ordermanager.generic.repository.GenericRepository;
import org.springframework.data.jpa.repository.Query;

public interface OrderRepository extends GenericRepository<Order, Long> {

    @Query(value = "SELECT * FROM orders ORDER BY id DESC LIMIT 1", nativeQuery = true)
    Order findLastOrder();
}
