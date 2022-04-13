package com.temzu.monomarket.services;

import com.temzu.monomarket.dtos.ProductDto;
import com.temzu.monomarket.dtos.ProductCreateDto;
import java.util.List;

public interface ProductService {

  List<ProductDto> findAll();

  ProductDto findById(Long id);

  void deleteById(Long id);

  void save(ProductCreateDto productCreateDto);

}
