package com.temzu.monomarket.models.mappers;

import com.temzu.monomarket.dtos.SignUpRequestDto;
import com.temzu.monomarket.models.User;
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
