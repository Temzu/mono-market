package com.temzu.monomarket.dao;

import com.temzu.monomarket.models.Role;

public interface RoleDao {

  Role findByName(String name);
}
