package com.temzu.monomarket.services;

import com.temzu.monomarket.dtos.OrderCreateDto;
import com.temzu.monomarket.dtos.OrderDto;
import org.springframework.data.domain.Page;

public interface OrderService {

  Page<OrderDto> findPageByCurrentUser(String login, int page, int pageSize);

  OrderDto findById(Long id);

  void createOrder(OrderCreateDto orderCreateDto);

}
