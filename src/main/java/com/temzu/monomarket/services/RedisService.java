package com.temzu.monomarket.services;

import java.util.Optional;

public interface RedisService<T> {

  Optional<T> getOptional(String key);

  T get(String key);

  boolean exists(String key);

  void set(String key, T o);

}
