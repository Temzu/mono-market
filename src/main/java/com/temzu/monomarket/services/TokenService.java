package com.temzu.monomarket.services;

import com.temzu.monomarket.models.UserInfo;

public interface TokenService {

  String generateTokenWithExpirationTime(UserInfo user);

  void expireToken(String token);

  UserInfo parseToken(String token);

  Long getUserId(String token);
}
