package com.temzu.monomarket.dao.models.mappers;

import com.temzu.monomarket.dao.models.User;
import com.temzu.monomarket.dtos.SignUpRequestDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserMapper {

  private final ModelMapper mapper;

  public User toUser(SignUpRequestDto signUpRequestDto) {
    return mapper.map(signUpRequestDto, User.class);
  }
}
