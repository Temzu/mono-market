package com.temzu.monomarket.dao;

import com.temzu.monomarket.dao.models.Category;

public interface CategoryDao {

  Category findById(Long id);

  Category findByTitle(String title);

}
