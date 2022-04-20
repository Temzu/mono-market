package com.temzu.monomarket.dtos;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class OrderItemDto {

  private Long productId;

  private String productTitle;

  private BigDecimal pricePerProduct;

  private BigDecimal price;

  private int quantity;
}
