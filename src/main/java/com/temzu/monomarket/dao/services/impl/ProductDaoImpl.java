package com.temzu.monomarket.dao.services.impl;

import com.temzu.monomarket.dao.models.Product;
import com.temzu.monomarket.dao.repositories.ProductRepository;
import com.temzu.monomarket.dao.services.ProductDao;
import com.temzu.monomarket.exceptions.ResourceNotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductDaoImpl implements ProductDao {

  private final ProductRepository productRepository;

  @Override
  public List<Product> findAll() {
    return productRepository.findAll();
  }

  @Override
  public Product findById(Long id) {
    return productRepository
        .findById(id)
        .orElseThrow(() -> ResourceNotFoundException.byId(id, Product.class));
  }

  @Override
  public void deleteById(Long id) {
    productRepository.delete(findById(id));
  }

  @Override
  public void save(Product product) {
    productRepository.save(product);
  }
}
