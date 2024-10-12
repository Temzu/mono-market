package com.temzu.monomarket.util;

import com.temzu.monomarket.dtos.OrderItemDto;
import com.temzu.monomarket.models.Product;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Data
public class Cart {
  private List<OrderItemDto> items;
  private BigDecimal price;

  public Cart() {
    this.items = new ArrayList<>();
    this.price = BigDecimal.ZERO;
  }

  public void clear() {
    items.clear();
    price = BigDecimal.ZERO;
  }

  public boolean add(Long productId) {
    BigDecimal tmp = BigDecimal.valueOf(price.doubleValue());
    items.stream()
        .filter(oid -> oid.getProductId().equals(productId))
        .findFirst()
        .ifPresent(
            oid -> {
              oid.changeQuantity(1);
              recalculate();
            });
    return price.compareTo(tmp) != 0;
  }

  public void add(Product product) {
    items.add(new OrderItemDto(product));
    recalculate();
  }

  private void recalculate() {
    price = BigDecimal.ZERO;
    items.forEach(oid -> price = price.add(oid.getPrice()));
  }

  public void remove(Long productId) {
    items.removeIf(oi -> oi.getProductId().equals(productId));
    recalculate();
  }

  public void changeQuantity(Long productId, int amount) {
    items.stream()
        .filter(oid -> oid.getProductId().equals(productId))
        .findFirst()
        .ifPresent(
            oid -> {
              oid.changeQuantity(amount);
              if (oid.getQuantity() <= 0) {
                items.remove(oid);
              }
              recalculate();
            });
  }

  public void merge(Cart another) {
    for (OrderItemDto anotherItem : another.items) {
      boolean merged = false;
      for (OrderItemDto myItem : items) {
        if (myItem.getProductId().equals(anotherItem.getProductId())) {
          myItem.changeQuantity(anotherItem.getQuantity());
          merged = true;
          break;
        }
      }
      if (!merged) {
        items.add(anotherItem);
      }
    }
    recalculate();
    another.clear();
  }
}
