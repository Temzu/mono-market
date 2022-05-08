package com.temzu.monomarket.services;

import com.temzu.monomarket.dtos.AuthRequestDto;
import com.temzu.monomarket.dtos.AuthResponseDto;
import com.temzu.monomarket.dtos.SignUpRequestDto;

public interface AuthService {

  AuthResponseDto signUp(SignUpRequestDto signUpRequestDto);

  AuthResponseDto login(AuthRequestDto authRequestDto);

  void logout(String token);
}
