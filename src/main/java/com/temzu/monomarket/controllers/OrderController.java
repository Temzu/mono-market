package com.temzu.monomarket.controllers;

import com.temzu.monomarket.dtos.OrderDto;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {



  @GetMapping
  public Page<OrderDto> findAll() {
    return null;
  }

}
