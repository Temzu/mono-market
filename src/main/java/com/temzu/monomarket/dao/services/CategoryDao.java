package com.temzu.monomarket.dao.services;

import com.temzu.monomarket.dao.models.Category;

public interface CategoryDao {

  Category findById(Long id);

}
