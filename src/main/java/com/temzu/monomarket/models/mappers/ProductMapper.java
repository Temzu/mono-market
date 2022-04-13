package com.temzu.monomarket.models.mappers;

import com.temzu.monomarket.dtos.ProductCreateDto;
import com.temzu.monomarket.dtos.ProductDto;
import com.temzu.monomarket.models.Product;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductMapper {

  private final ModelMapper mapper;

  public Product toProduct(ProductDto productDto) {
    return mapper.map(productDto, Product.class);
  }

  public Product toProduct(ProductCreateDto productCreateDto) {
    return mapper.map(productCreateDto, Product.class);
  }

  public ProductDto toProductDto(Product product) {
    return mapper.map(product, ProductDto.class);
  }
}
