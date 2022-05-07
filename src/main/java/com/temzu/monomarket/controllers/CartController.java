package com.temzu.monomarket.controllers;

import com.temzu.monomarket.dtos.StringResponse;
import com.temzu.monomarket.services.CartService;
import com.temzu.monomarket.util.Cart;
import java.security.Principal;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
@Slf4j
public class CartController {

  private final CartService cartService;

  @GetMapping("/{uuid}")
  public Cart getCart(Principal principal, @PathVariable String uuid) {
    return cartService.getCurrentCart(getCurrentCartUuid(principal, uuid));
  }

  @GetMapping("/generate")
  public StringResponse getCart() {
    return new StringResponse(cartService.generateCartUuid());
  }

  @GetMapping("/{uuid}/add/{productId}")
  public void add(Principal principal, @PathVariable String uuid, @PathVariable Long productId) {
    cartService.addToCart(getCurrentCartUuid(principal, uuid), productId);
  }

  @GetMapping("/{uuid}/decrement/{productId}")
  public void decrement(
      Principal principal, @PathVariable String uuid, @PathVariable Long productId) {
    cartService.decrementQuantity(getCurrentCartUuid(principal, uuid), productId);
  }

  @GetMapping("/{uuid}/remove/{productId}")
  public void remove(Principal principal, @PathVariable String uuid, @PathVariable Long productId) {
    cartService.removeItemFromCart(getCurrentCartUuid(principal, uuid), productId);
  }

  @GetMapping("/{uuid}/clear")
  public void clear(@PathVariable String uuid) {
    cartService.clearCart(uuid);
  }

  @GetMapping("/{uuid}/merge")
  public void merge(Principal principal, @PathVariable String uuid) {
    cartService.merge(getCurrentCartUuid(principal, null), getCurrentCartUuid(null, uuid));
  }

  private String getCurrentCartUuid(Principal principal, String uuid) {
    if (principal != null) {
      return cartService.getCartUuidFromSuffix(principal.getName());
    }
    return cartService.getCartUuidFromSuffix(uuid);
  }
}
