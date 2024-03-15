package com.roberth.ordermanager.domain.order.controller;

import com.roberth.ordermanager.domain.order.dto.OrderCreateDto;
import com.roberth.ordermanager.domain.order.dto.OrderUpdateDto;
import com.roberth.ordermanager.domain.order.entity.Order;
import com.roberth.ordermanager.domain.order.service.OrderService;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public List<Order> getAll() {
        return this.orderService.getAll();
    }

    @GetMapping("/{id}")
    public Optional<Order> getById(@PathVariable Long id) {
        return this.orderService.getById(id);
    }

    @PostMapping
    public Order create(@RequestBody OrderCreateDto orderCreateDto) throws BadRequestException {
        return this.orderService.save(orderCreateDto);
    }

    @PutMapping("/{id}")
    public Order updateOrder(@PathVariable Long id, @RequestBody OrderUpdateDto orderUpdateDto) throws BadRequestException {
        return orderService.update(orderUpdateDto);
    }

    @DeleteMapping("/{id}")
    public void deleteOrder(@PathVariable Long id) throws BadRequestException {
        orderService.delete(id);
    }


}
