package com.temzu.monomarket.services.impl;

import com.temzu.monomarket.dao.services.ProductDao;
import com.temzu.monomarket.dtos.ProductCreateDto;
import com.temzu.monomarket.dtos.ProductDto;
import com.temzu.monomarket.exceptions.ResourceNotFoundException;
import com.temzu.monomarket.dao.models.Product;
import com.temzu.monomarket.dao.models.mappers.ProductMapper;
import com.temzu.monomarket.dao.repositories.ProductRepository;
import com.temzu.monomarket.services.ProductService;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

  private final ProductDao productDao;
  private final ProductMapper productMapper;

  @Override
  public List<ProductDto> findAll() {
    return productDao.findAll().stream()
        .map(productMapper::toProductDto)
        .collect(Collectors.toList());
  }

  @Override
  public ProductDto findById(Long id) {
    return productMapper.toProductDto(productDao.findById(id));
  }

  @Override
  public void deleteById(Long id) {
    productDao.deleteById(id);
  }

  @Override
  public void save(ProductCreateDto productCreateDto) {
    productDao.save(productMapper.toProduct(productCreateDto));
  }
}
