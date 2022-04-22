package com.temzu.monomarket.services.impl;

import com.temzu.monomarket.dao.OrderDao;
import com.temzu.monomarket.dao.UserDao;
import com.temzu.monomarket.dtos.OrderCreateDto;
import com.temzu.monomarket.dtos.OrderDto;
import com.temzu.monomarket.models.User;
import com.temzu.monomarket.models.mappers.OrderMapper;
import com.temzu.monomarket.services.OrderService;
import com.temzu.monomarket.services.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

  private final OrderDao orderDao;

  private final UserDao userDao;

  private final OrderMapper orderMapper;

  @Override
  public Page<OrderDto> findPageByCurrentUser(String login, int page, int pageSize) {
    User curUser = userDao.findByLogin(login);
    return orderDao.findPageByUser(curUser, page, pageSize).map(orderMapper::toOrderDto);
  }

  @Override
  public OrderDto findById(Long id) {
    return null;
  }

  @Override
  public void createOrder(OrderCreateDto orderCreateDto) {}
}
