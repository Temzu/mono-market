package com.temzu.monomarket.controllers;

import com.temzu.monomarket.dtos.OrderDto;
import com.temzu.monomarket.services.OrderService;
import java.security.Principal;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
@PreAuthorize("isAuthenticated()")
public class OrderController {

  private final OrderService orderService;

  @GetMapping
  public Page<OrderDto> findPageByCurrentUser(
      Principal principal,
      @RequestParam(name = "page", defaultValue = "1") int page,
      @RequestParam(name = "page_size", defaultValue = "10") int pageSize
  ) {
    if (page < 1 || pageSize < 1) {
      page = 1;
      pageSize = 10;
    }
    return orderService.findPageByUserLogin(principal.getName(), page, pageSize);
  }



}
