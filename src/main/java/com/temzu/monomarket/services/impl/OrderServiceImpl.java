package com.temzu.monomarket.services.impl;

import com.temzu.monomarket.dao.OrderDao;
import com.temzu.monomarket.dao.ProductDao;
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
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

  private final OrderDao orderDao;

  private final UserDao userDao;

  private final CartService cartService;

  private final RedisService<Cart> redisService;

  private final OrderItemMapper orderItemMapper;

  private final OrderMapper orderMapper;

  @Override
  public Page<OrderDto> findPageByUserLogin(String login, int page, int pageSize) {
    User curUser = userDao.findByLogin(login);
    return orderDao.findPageByUser(curUser, page, pageSize).map(orderMapper::toOrderDto);
  }
  @Transactional
  @Override
  public void createOrder(String login, String address, String phone, String uuid) {
    Order order = new Order();

    Cart cart = cartService.getCurrentCart(uuid);
    User curUser = userDao.findByLogin(login);
    List<OrderItem> orderItems =
        cart.getItems().stream()
            .map(
                orderItemDto -> {
                  OrderItem orderItem = orderItemMapper.toOrderItem(orderItemDto);
                  orderItem.setOrder(order);
                  return orderItem;
                })
            .collect(Collectors.toList());

    order.setPhone(phone);
    order.setAddress(address);
    order.setItems(orderItems);
    order.setUser(curUser);
    order.setPrice(cart.getPrice());

    cart.clear();

    orderDao.saveOrUpdate(order);
    redisService.set(uuid, cart);
  }
}
