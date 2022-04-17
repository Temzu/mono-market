package com.temzu.monomarket.services;

import com.temzu.monomarket.dao.models.UserInfo;

public interface TokenService {

  String generateToken(UserInfo user);

  UserInfo parseToken(String token);

  Long getUserId(String token);
}
