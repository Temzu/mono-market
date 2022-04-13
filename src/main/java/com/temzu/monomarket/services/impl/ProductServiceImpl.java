package com.temzu.monomarket.services.impl;

import com.temzu.monomarket.dtos.ProductCreateDto;
import com.temzu.monomarket.dtos.ProductDto;
import com.temzu.monomarket.exceptions.ResourceNotFoundException;
import com.temzu.monomarket.models.Product;
import com.temzu.monomarket.models.mappers.ProductMapper;
import com.temzu.monomarket.repositories.ProductRepository;
import com.temzu.monomarket.services.ProductService;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

  private final ProductRepository productRepository;
  private final ProductMapper productMapper;

  @Override
  public List<ProductDto> findAll() {
    return productRepository.findAll().stream()
        .map(productMapper::toProductDto)
        .collect(Collectors.toList());
  }

  @Override
  public ProductDto findById(Long id) {
    Product product = productRepository
        .findById(id)
        .orElseThrow(() -> ResourceNotFoundException.byId(id, Product.class));
    return productMapper.toProductDto(product);
  }

  @Override
  public void deleteById(Long id) {
    productRepository.deleteById(id);
  }

  @Override
  public void save(ProductCreateDto productCreateDto) {
    productRepository.save(productMapper.toProduct(productCreateDto));
  }
}
