package com.temzu.monomarket.dao.repositories;

import com.temzu.monomarket.dao.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long  > {

}
