package com.temzu.monomarket.dao.services;

import com.temzu.monomarket.dao.models.Product;
import java.util.List;

public interface ProductDao {

  List<Product> findAll();

  Product findById(Long id);

  void deleteById(Long id);

  void save(Product product);

}
