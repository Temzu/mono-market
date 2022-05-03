package com.temzu.monomarket.services;

import com.temzu.monomarket.util.Cart;

public interface CartService {
  String generateCartUuid();

  Cart getCurrentCart(String cartKey);

  void addToCart(String cartKey, Long productId);

  void clearCart(String cartKey);

  void decrementQuantity(String cartKey, Long productId);


}
