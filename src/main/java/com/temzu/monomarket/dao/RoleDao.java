package com.temzu.monomarket.dao;

import com.temzu.monomarket.dao.models.Role;

public interface RoleDao {

  Role findByName(String name);
}
