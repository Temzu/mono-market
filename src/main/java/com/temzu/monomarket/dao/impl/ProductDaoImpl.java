package com.temzu.monomarket.dao.impl;

import com.temzu.monomarket.dao.models.Product;
import com.temzu.monomarket.dao.repositories.ProductRepository;
import com.temzu.monomarket.dao.ProductDao;
import com.temzu.monomarket.dtos.ProductDto;
import com.temzu.monomarket.exceptions.ResourceNotFoundException;
import java.util.List;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductDaoImpl implements ProductDao {

  private final ProductRepository productRepository;

  @Override
  public Page<Product> findPage(@NonNull Specification<Product> spec, int page, int pageSize) {
    return productRepository.findAll(spec, PageRequest.of(page - 1, pageSize));
  }

  @Override
  public Product findById(Long id) {
    return productRepository
        .findById(id)
        .orElseThrow(() -> ResourceNotFoundException.byId(id, Product.class));
  }

  @Override
  public Product save(Product product) {
    return productRepository.save(product);
  }

  @Override
  public Product update(Product product) {
    return productRepository.save(product);
  }

  @Override
  public void deleteById(Long id) {
    productRepository.delete(findById(id));
  }
}
