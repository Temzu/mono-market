package com.temzu.monomarket.dao;

import com.temzu.monomarket.dao.models.Product;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;

public interface ProductDao {

  Page<Product> findPage(Specification<Product> spec, int page, int pageSize);

  Product findById(Long id);

  void deleteById(Long id);

  void save(Product product);

}
