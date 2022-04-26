package com.temzu.monomarket.services;

import com.temzu.monomarket.util.Cart;
import java.util.Optional;
import java.util.UUID;

public interface CartService {
  String generateCartUuid();

  Cart getCurrentCart(String cartKey);

  void addToCart(String cartKey, Long productId);

  void clearCart(String cartKey);

  void decrementQuantity(String cartKey, Long productId);


}
