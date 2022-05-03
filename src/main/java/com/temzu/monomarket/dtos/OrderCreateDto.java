package com.temzu.monomarket.dtos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrderCreateDto {

  @NotBlank(message = "Address must not be blank and not be null")
  private String address;

  @NotBlank(message = "Phone must not be blank and not be null")
  @Size(min = 11, max = 11, message = "Phone length must be 11")
  @Pattern(regexp="^\\d*$", message = "Phone contains invalid characters")
  private String phone;
}
