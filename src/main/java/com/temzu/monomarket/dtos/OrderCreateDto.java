package com.temzu.monomarket.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrderCreateDto {

  private String address;

  private String phone;
}
