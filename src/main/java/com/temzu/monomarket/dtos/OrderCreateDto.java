package com.temzu.monomarket.dtos;

import java.util.UUID;
import lombok.Data;

@Data
public class OrderCreateDto {
  private UUID cartUuid;
  private String address;
  private String phone;
}
