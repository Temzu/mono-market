package com.temzu.monomarket.dao;

import com.temzu.monomarket.dao.models.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;

public interface ProductDao {

  Page<Product> findPage(Specification<Product> spec, int page, int pageSize);

  Product findById(Long id);

  Product save(Product product);

  Product update(Product product);

  void deleteById(Long id);

}
