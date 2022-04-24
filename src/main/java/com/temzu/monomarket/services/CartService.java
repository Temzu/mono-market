package com.temzu.monomarket.services;

import com.temzu.monomarket.util.Cart;
import java.util.Optional;
import java.util.UUID;

public interface CartService {
  Cart save(Cart cart);

  Cart findById(UUID id);

  Optional<Cart> findByUserId(Long userId);

  void addToCart(UUID cartId, Long productId);

  void clearCart(UUID cartId);

  UUID findCartForUser(Long userId, UUID cartUuid);
}
