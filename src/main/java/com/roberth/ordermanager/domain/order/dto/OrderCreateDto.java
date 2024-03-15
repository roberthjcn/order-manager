package com.roberth.ordermanager.domain.order.dto;

import lombok.Data;

import java.util.List;

@Data
public class OrderCreateDto {
    private Long clientId;
    private List<Long> articleIds;
}
