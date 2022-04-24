package com.temzu.monomarket.controllers;

import com.temzu.monomarket.dao.ProductDao;
import com.temzu.monomarket.util.Cart;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
public class CartController {

  private final Cart cart;
  private final ProductDao productDao;

  @GetMapping
  public Cart getCart() {
    return cart;
  }

  @GetMapping("/add/{productId}")
  public void add(@PathVariable Long productId) {
    if (!cart.add(productId)) {
      cart.add(productDao.findById(productId));
    }
  }

  @GetMapping("/decrement/{productId}")
  public void decrement(@PathVariable Long productId) {
    cart.changeQuantity(productId, -1);
  }

  @GetMapping("/remove/{productId}")
  public void remove(@PathVariable Long productId) {
    cart.remove(productId);
  }

  @GetMapping("/clear")
  public void clear() {
    cart.clear();
  }

}
