package com.roberth.ordermanager.domain.order.dto;

import lombok.Data;

import java.util.List;

@Data
public class OrderUpdateDto {
    private Long id;
    private Long clientId;
    private List<Long> articleIds;
}
