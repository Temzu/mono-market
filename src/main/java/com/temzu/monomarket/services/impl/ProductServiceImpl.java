package com.temzu.monomarket.services.impl;

import com.temzu.monomarket.dao.ProductDao;
import com.temzu.monomarket.dao.models.Product;
import com.temzu.monomarket.dao.repositories.specification.ProductSpecifications;
import com.temzu.monomarket.dtos.ProductCreateDto;
import com.temzu.monomarket.dtos.ProductDto;
import com.temzu.monomarket.dao.models.mappers.ProductMapper;
import com.temzu.monomarket.services.ProductService;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

  private final ProductDao productDao;
  private final ProductMapper productMapper;

  @Override
  public Page<ProductDto> findPage(
      MultiValueMap<String, String> params,
      Integer page,
      Integer pageSize
  ) {
    Specification<Product> specification = ProductSpecifications.build(params);
    return productDao
        .findPage(specification, page, pageSize)
        .map(productMapper::toProductDto);
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
