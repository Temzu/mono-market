package com.temzu.monomarket.models.mappers;

import com.temzu.monomarket.dtos.OrderDto;
import com.temzu.monomarket.models.Order;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderMapper {

  private final ModelMapper mapper;

  public OrderDto toOrderDto(Order order) {
    return mapper.map(order, OrderDto.class);
  }
}
