package com.temzu.monomarket.dtos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import lombok.Data;

@Data
public class AuthRequestDto {

  @NotBlank(message = "Login must not be blank and not be null")
  @Size(min = 4, max = 30, message = "Login length must be between 4-30")
  @Pattern(regexp="^[A-Za-z0-9]*$", message = "Login contains invalid characters")
  private String login;

  @NotBlank(message = "Password must not be blank and not be null")
  @Size(min = 6, max = 55, message = "Password length must be between 6-55")
  private String password;
}
