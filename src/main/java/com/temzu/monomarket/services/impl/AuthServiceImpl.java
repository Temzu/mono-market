package com.temzu.monomarket.services.impl;

import com.temzu.monomarket.dao.UserDao;
import com.temzu.monomarket.dao.models.Role;
import com.temzu.monomarket.dao.models.User;
import com.temzu.monomarket.dao.models.UserInfo;
import com.temzu.monomarket.dao.models.mappers.UserMapper;
import com.temzu.monomarket.dtos.AuthRequestDto;
import com.temzu.monomarket.dtos.AuthResponseDto;
import com.temzu.monomarket.dtos.SignUpRequestDto;
import com.temzu.monomarket.services.AuthService;
import com.temzu.monomarket.services.TokenService;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

  private final UserDao userDao;
  private final TokenService tokenService;
  private final UserMapper userMapper;

  @Override
  public AuthResponseDto signUp(SignUpRequestDto signUpRequestDto) {
    User user = userDao.save(userMapper.toUser(signUpRequestDto));

    String token = returnToken(user);
    return new AuthResponseDto(token);
  }

  @Override
  public AuthResponseDto login(AuthRequestDto request) {
    User user = userDao.findByLoginAndPassword(request.getLogin(), request.getPassword());

    String token = returnToken(user);
    return new AuthResponseDto(token);
  }

  private String returnToken(User user) {
    UserInfo userInfo = UserInfo.builder()
        .userId(user.getId())
        .userEmail(user.getLogin())
        .roles(user.getRoles().stream().map(Role::getName).collect(Collectors.toList()))
        .build();
    return tokenService.generateToken(userInfo);
  }
}
