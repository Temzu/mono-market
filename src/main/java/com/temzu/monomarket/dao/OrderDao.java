package com.temzu.monomarket.dao;

import com.temzu.monomarket.models.Order;
import com.temzu.monomarket.models.User;
import org.springframework.data.domain.Page;

public interface OrderDao {

  Page<Order> findPageByUser(User user, int page, int pageSize);

  Order saveOrUpdate(Order order);

}
