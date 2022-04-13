package com.temzu.monomarket.dtos;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class ProductCreateDto {

  private String title;
  private BigDecimal price;
}
