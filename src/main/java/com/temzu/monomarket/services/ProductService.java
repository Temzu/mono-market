package com.temzu.monomarket.services;

import com.temzu.monomarket.dtos.ProductDto;
import com.temzu.monomarket.dtos.ProductCreateDto;
import org.springframework.data.domain.Page;
import org.springframework.util.MultiValueMap;

public interface ProductService {

  Page<ProductDto> findPage(MultiValueMap<String, String> params, Integer page, Integer pageSize);

  ProductDto findById(Long id);

  void deleteById(Long id);

  void save(ProductCreateDto productCreateDto);

}
