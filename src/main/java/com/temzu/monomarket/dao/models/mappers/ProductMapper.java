package com.temzu.monomarket.dao.models.mappers;

import com.temzu.monomarket.dao.services.CategoryDao;
import com.temzu.monomarket.dtos.ProductCreateDto;
import com.temzu.monomarket.dtos.ProductDto;
import com.temzu.monomarket.dao.models.Product;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductMapper {

  private final ModelMapper mapper;
  private final CategoryDao categoryDao;

  public Product toProduct(ProductDto productDto) {
    return mapper.map(productDto, Product.class);
  }

  public Product toProduct(ProductCreateDto productCreateDto) {
    Product product = mapper.map(productCreateDto, Product.class);
    product.setId(null);
    product.setCategory(categoryDao.findById(productCreateDto.getCategoryId()));
    return product;
  }

  public ProductDto toProductDto(Product product) {
    return mapper.map(product, ProductDto.class);
  }
}
