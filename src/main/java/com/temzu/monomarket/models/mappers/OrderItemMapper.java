package com.temzu.monomarket.models.mappers;

import com.temzu.monomarket.dtos.OrderItemDto;
import com.temzu.monomarket.models.OrderItem;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderItemMapper {

  private final ModelMapper mapper;

  public OrderItem toOrderItem(OrderItemDto orderItemDto) {
    OrderItem orderItem = mapper.map(orderItemDto, OrderItem.class);
    orderItem.setId(null);
    return orderItem;
  }
}
