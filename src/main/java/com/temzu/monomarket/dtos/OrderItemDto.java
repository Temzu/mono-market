package com.temzu.monomarket.dtos;

import com.temzu.monomarket.models.Product;
import java.math.BigDecimal;
import lombok.Data;

@Data
public class OrderItemDto {

  private Long productId;

  private String productTitle;

  private BigDecimal pricePerProduct;

  private BigDecimal price;

  private int quantity;

  public OrderItemDto(Product product) {
    this.productId = product.getId();
    this.quantity = 1;
    this.pricePerProduct = product.getPrice();
    this.price = product.getPrice();
    this.productTitle = product.getTitle();
  }

  public void changeQuantity(int amount) {
    quantity += amount;
    price = pricePerProduct.multiply(BigDecimal.valueOf(quantity));
  }
}
