package com.temzu.monomarket.controllers;

import com.temzu.monomarket.dtos.OrderCreateDto;
import com.temzu.monomarket.dtos.OrderDto;
import com.temzu.monomarket.services.OrderService;
import java.security.Principal;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
@PreAuthorize("isAuthenticated()")
@Slf4j
public class OrderController {

  private final OrderService orderService;

  @GetMapping
  public Page<OrderDto> findPageByCurrentUser(
      Principal principal,
      @RequestParam(name = "page", defaultValue = "1") int page,
      @RequestParam(name = "page_size", defaultValue = "10") int pageSize) {
    if (page < 1 || pageSize < 1) {
      page = 1;
      pageSize = 10;
    }
    return orderService.findPageByUserLogin(principal.getName(), page, pageSize);
  }

  @PostMapping("/{uuid}")
  public void createOrder(
      Principal principal,
      @Valid @RequestBody OrderCreateDto orderCreateDto,
      @PathVariable String uuid) {
    orderService.createOrder(principal.getName(), orderCreateDto, uuid);
  }
}
