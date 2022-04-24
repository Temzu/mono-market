package com.temzu.monomarket.services.impl;

import com.temzu.monomarket.dao.OrderDao;
import com.temzu.monomarket.dao.UserDao;
import com.temzu.monomarket.dtos.OrderCreateDto;
import com.temzu.monomarket.dtos.OrderDto;
import com.temzu.monomarket.models.Order;
import com.temzu.monomarket.models.User;
import com.temzu.monomarket.models.mappers.OrderMapper;
import com.temzu.monomarket.services.CartService;
import com.temzu.monomarket.services.OrderService;
import com.temzu.monomarket.services.TokenService;
import com.temzu.monomarket.util.Cart;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

  private final OrderDao orderDao;

  private final UserDao userDao;

  private final CartService cartService;

  private final OrderMapper orderMapper;

  @Override
  public Page<OrderDto> findPageByUserLogin(String login, int page, int pageSize) {
    User curUser = userDao.findByLogin(login);
    return orderDao.findPageByUser(curUser, page, pageSize).map(orderMapper::toOrderDto);
  }

  @Override
  public void createOrder(String login, OrderCreateDto orderCreateDto) {
    Cart cart = cartService.findById(orderCreateDto.getCartUuid());
    User curUser = userDao.findByLogin(login);
    Order order = new Order(cart, curUser, orderCreateDto);
    orderDao.saveOrUpdate(order);
  }

}
