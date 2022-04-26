package com.temzu.monomarket.dao.impl;

import com.temzu.monomarket.dao.OrderDao;
import com.temzu.monomarket.dao.repositories.OrderRepository;
import com.temzu.monomarket.models.Order;
import com.temzu.monomarket.models.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderDaoImpl implements OrderDao {

  private final OrderRepository orderRepository;

  @Override
  public Page<Order> findPageByUser(User user, int page, int pageSize) {
    return orderRepository.findAllByUser(user, PageRequest.of(page - 1, pageSize));
  }

  @Override
  public Order saveOrUpdate(Order order) {
    return orderRepository.save(order);
  }
}
