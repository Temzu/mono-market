package com.temzu.monomarket.dtos;

import java.math.BigDecimal;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;

@Data
public class ProductUpdateDto {

  @NotNull(message = "Id must be not null")
  @Min(value = 1, message = "Id must be greater than or equals to 1")
  private Long id;

  @NotBlank(message = "Title must not be blank")
  @NotNull(message = "Title must be not null")
  @Size(min = 3, max = 255, message = "Title length must be between 3-255")
  private String title;

  @NotNull(message = "Price must be not null")
  @Min(value = 1, message = "Price must be greater than or equal to 1")
  private BigDecimal price;

  @NotBlank(message = "Category title must not be blank")
  @NotNull(message = "Category title must be not null")
  private String categoryTitle;
}
