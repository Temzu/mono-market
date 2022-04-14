package com.temzu.monomarket.dao.services.impl;

import com.temzu.monomarket.dao.models.Category;
import com.temzu.monomarket.dao.repositories.CategoryRepository;
import com.temzu.monomarket.dao.services.CategoryDao;
import com.temzu.monomarket.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryDaoImpl implements CategoryDao {

  private final CategoryRepository categoryRepository;

  @Override
  public Category findById(Long id) {
    return categoryRepository
        .findById(id)
        .orElseThrow(() -> ResourceNotFoundException.byId(id, Category.class));
  }
}
