package com.temzu.monomarket.dtos;

import java.math.BigDecimal;
import java.util.List;
import lombok.Data;

@Data
public class OrderDto {

  private Long id;

  private String address;

  private String phone;

  private BigDecimal price;

  private List<OrderItemDto> items;
}
