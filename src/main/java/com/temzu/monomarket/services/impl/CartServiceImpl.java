package com.temzu.monomarket.services.impl;

import com.temzu.monomarket.dao.ProductDao;
import com.temzu.monomarket.services.CartService;
import com.temzu.monomarket.services.RedisService;
import com.temzu.monomarket.util.Cart;
import java.util.UUID;
import java.util.function.Consumer;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

  private final ProductDao productDao;

  private final RedisService<Cart> redisService;

  @Value("${utils.cart.prefix}")
  private String cartPrefix;

  @Override
  public String generateCartUuid() {
    return UUID.randomUUID().toString();
  }

  @Override
  public String getCartUuidFromSuffix(String suffix) {
    return cartPrefix + suffix;
  }

  @Override
  public Cart getCurrentCart(String cartKey) {
    return redisService
        .getOptional(cartKey)
        .orElseGet(
            () -> {
              redisService.set(cartKey, new Cart());
              return redisService.get(cartKey);
            });
  }

  @Override
  public void addToCart(String cartKey, Long productId) {
    execute(
        cartKey,
        cart -> {
          if (!cart.add(productId)) {
            cart.add(productDao.findById(productId));
          }
        });
  }

  @Override
  public void clearCart(String cartKey) {
    execute(cartKey, Cart::clear);
  }

  @Override
  public void decrementQuantity(String cartKey, Long productId) {
    execute(cartKey, cart -> cart.changeQuantity(productId, -1));
  }

  @Override
  public void removeItemFromCart(String cartKey, Long productId) {
    execute(cartKey, cart -> cart.remove(productId));
  }

  @Override
  public void merge(String userCartKey, String guestCartKey) {
    Cart guestCart = getCurrentCart(guestCartKey);
    Cart userCart = getCurrentCart(userCartKey);
    userCart.merge(guestCart);
    redisService.set(userCartKey, userCart);
    redisService.set(guestCartKey, guestCart);
  }

  private void execute(String cartKey, Consumer<Cart> action) {
    Cart cart = getCurrentCart(cartKey);
    action.accept(cart);
    redisService.set(cartKey, cart);
  }
}
