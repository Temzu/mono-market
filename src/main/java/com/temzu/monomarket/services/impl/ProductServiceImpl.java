package com.temzu.monomarket.services.impl;

import com.temzu.monomarket.dao.CategoryDao;
import com.temzu.monomarket.dao.ProductDao;
import com.temzu.monomarket.models.Product;
import com.temzu.monomarket.dao.repositories.specification.ProductSpecifications;
import com.temzu.monomarket.dtos.ProductCreateDto;
import com.temzu.monomarket.dtos.ProductDto;
import com.temzu.monomarket.models.mappers.ProductMapper;
import com.temzu.monomarket.dtos.ProductUpdateDto;
import com.temzu.monomarket.exceptions.ResourceNotFoundException;
import com.temzu.monomarket.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.MultiValueMap;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

  private final ProductDao productDao;
  private final CategoryDao categoryDao;
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

  @Transactional
  @Override
  public ProductDto save(ProductCreateDto productCreateDto) {
    Product createdProd = productMapper.toProduct(productCreateDto);
    createdProd.setCategory(categoryDao.findByTitle(productCreateDto.getCategoryTitle()));
    return productMapper.toProductDto(productDao.saveOrUpdate(createdProd));
  }

  @Transactional
  @Override
  public ProductDto update(ProductUpdateDto productUpdateDto) {
    if (!productDao.existById(productUpdateDto.getId())) {
      throw ResourceNotFoundException.byId(productUpdateDto.getId(), Product.class);
    }
    Product updatedProd = productMapper.toProduct(productUpdateDto);
    updatedProd.setCategory(categoryDao.findByTitle(productUpdateDto.getCategoryTitle()));
    return productMapper.toProductDto(productDao.saveOrUpdate(updatedProd));
  }

  @Transactional
  @Override
  public void deleteById(Long id) {
    productDao.deleteById(id);
  }
}
