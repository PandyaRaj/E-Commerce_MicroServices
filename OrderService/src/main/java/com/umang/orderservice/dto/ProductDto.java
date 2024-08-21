package com.umang.orderservice.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductDto {
    private int id;
    private String name;
    private String description;
    private BigDecimal price;
    private Integer stock;
    private String imageId;
}
