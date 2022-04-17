package com.temzu.monomarket.controllers;

import com.temzu.monomarket.dtos.ProductCreateDto;
import com.temzu.monomarket.dtos.ProductDto;
import com.temzu.monomarket.dtos.ProductUpdateDto;
import com.temzu.monomarket.services.ProductService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
//@PreAuthorize("isAuthenticated()")
public class ProductController {

  private final ProductService productService;

  @GetMapping
  public Page<ProductDto> findPage(
      @RequestParam MultiValueMap<String, String> params,
      @RequestParam(name = "page", defaultValue = "1") Integer page,
      @RequestParam(name = "size", defaultValue = "10") Integer pageSize) {
    if (page < 1 || pageSize < 1) {
      page = 1;
      pageSize = 10;
    }
    return productService.findPage(params, page, pageSize);
  }


  @GetMapping("/{id}")
  public ProductDto findById(@PathVariable Long id) {
    return productService.findById(id);
  }

  @PostMapping
  public ProductDto save(@Valid @RequestBody ProductCreateDto productCreateDto) {
    return productService.save(productCreateDto);
  }

  @PutMapping
  public ProductDto update(@Valid @RequestBody ProductUpdateDto productUpdateDto) {
    return productService.update(productUpdateDto);
  }

  @DeleteMapping("/{id}")
  public void deleteById(@PathVariable Long id) {
    productService.deleteById(id);
  }
}
