package com.temzu.monomarket.services.impl;

import com.temzu.monomarket.dao.OrderDao;
import com.temzu.monomarket.dao.UserDao;
import com.temzu.monomarket.dtos.OrderCreateDto;
import com.temzu.monomarket.dtos.OrderDto;
import com.temzu.monomarket.models.Order;
import com.temzu.monomarket.models.OrderItem;
import com.temzu.monomarket.models.User;
import com.temzu.monomarket.models.mappers.OrderItemMapper;
import com.temzu.monomarket.models.mappers.OrderMapper;
import com.temzu.monomarket.services.CartService;
import com.temzu.monomarket.services.OrderService;
import com.temzu.monomarket.services.RedisService;
import com.temzu.monomarket.util.Cart;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

  private final OrderDao orderDao;

  private final UserDao userDao;

  private final CartService cartService;

  private final RedisService<Cart> redisService;

  private final OrderItemMapper orderItemMapper;

  private final OrderMapper orderMapper;

  @Transactional(readOnly = true)
  @Override
  public Page<OrderDto> findPageByUserLogin(String login, int page, int pageSize) {
    User curUser = userDao.findByLogin(login);
    return orderDao
        .findPageByUser(curUser, page, pageSize)
        .map(orderMapper::toOrderDto);
  }

  @Transactional
  @Override
  public void createOrder(String login, OrderCreateDto orderCreateDto) {
    Cart cart = cartService.getCurrentCart(cartService.getCartUuidFromSuffix(login));
    Order order =
        Order.builder()
            .phone(orderCreateDto.getPhone())
            .address(orderCreateDto.getAddress())
            .user(userDao.findByLogin(login))
            .price(cart.getPrice())
            .build();

    order.setItems(
        cart.getItems().stream()
            .map(
                oid -> {
                  OrderItem orderItem = orderItemMapper.toOrderItem(oid);
                  orderItem.setOrder(order);
                  return orderItem;
                })
            .collect(Collectors.toList()));
    cart.clear();
    orderDao.saveOrUpdate(order);
    redisService.set(cartService.getCartUuidFromSuffix(login), cart);
  }
}
