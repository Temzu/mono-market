package com.temzu.monomarket.dao;

import com.temzu.monomarket.models.User;

public interface UserDao {

  User save(User user);

  User findByLogin(String login);

  User findByLoginAndPassword(String login, String password);

}
