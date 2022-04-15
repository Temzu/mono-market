package com.temzu.monomarket.dtos;

import java.math.BigDecimal;
import lombok.Data;
import lombok.NonNull;

@Data
public class ProductCreateDto {

  @NonNull
  private String title;

  @NonNull
  private BigDecimal price;

  @NonNull
  private Long categoryId;
}
