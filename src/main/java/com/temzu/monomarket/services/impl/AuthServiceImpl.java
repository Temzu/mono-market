package com.temzu.monomarket.services.impl;

import com.temzu.monomarket.dao.UserDao;
import com.temzu.monomarket.models.Role;
import com.temzu.monomarket.models.User;
import com.temzu.monomarket.models.UserInfo;
import com.temzu.monomarket.models.mappers.UserMapper;
import com.temzu.monomarket.dtos.AuthRequestDto;
import com.temzu.monomarket.dtos.AuthResponseDto;
import com.temzu.monomarket.dtos.SignUpRequestDto;
import com.temzu.monomarket.services.AuthService;
import com.temzu.monomarket.services.TokenService;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

  private final UserDao userDao;
  private final TokenService tokenService;
  private final UserMapper userMapper;

  @Transactional
  @Override
  public AuthResponseDto signUp(SignUpRequestDto signUpRequestDto) {
    User user = userDao.save(userMapper.toUser(signUpRequestDto));
    String token = returnToken(user);
    return new AuthResponseDto(token);
  }

  @Transactional
  @Override
  public AuthResponseDto login(AuthRequestDto request) {
    User user = userDao.findByLoginAndPassword(request.getLogin(), request.getPassword());
    String token = returnToken(user);
    return new AuthResponseDto(token);
  }

  @Override
  public void logout(String token) {
    tokenService.expireToken(token);
  }

  private String returnToken(User user) {
    UserInfo userInfo =
        UserInfo.builder()
            .userId(user.getId())
            .userLogin(user.getLogin())
            .userEmail(user.getEmail())
            .roles(user.getRoles().stream().map(Role::getName).collect(Collectors.toList()))
            .build();
    return "Bearer " + tokenService.generateTokenWithExpirationTime(userInfo);
  }
}
