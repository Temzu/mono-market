package com.temzu.monomarket.dao.impl;

import com.temzu.monomarket.dao.RoleDao;
import com.temzu.monomarket.models.Role;
import com.temzu.monomarket.dao.repositories.RoleRepository;
import com.temzu.monomarket.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleDaoImpl implements RoleDao {

  private final RoleRepository roleRepository;

  @Override
  public Role findByName(String name) {
    return roleRepository
        .findByName(name)
        .orElseThrow(() -> ResourceNotFoundException.byName(name, Role.class));
  }
}
