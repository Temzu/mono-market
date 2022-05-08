package com.temzu.monomarket.services;

import com.temzu.monomarket.util.Cart;

public interface CartService {
  String generateCartUuid();

  String getCartUuidFromSuffix(String suffix);

  Cart getCurrentCart(String cartKey);

  void addToCart(String cartKey, Long productId);

  void clearCart(String cartKey);

  void decrementQuantity(String cartKey, Long productId);

  void removeItemFromCart(String cartKey, Long productId);

  void merge(String userCartKey, String guestCartKey);
}
