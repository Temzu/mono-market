package com.temzu.monomarket.dao.repositories.specification;

import com.temzu.monomarket.models.Product;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.MultiValueMap;

public class ProductSpecifications {

  private static final String FILTER_MIN_PRICE = "min_price";
  private static final String FILTER_MAX_PRICE = "max_price";
  private static final String FILTER_TITLE = "title";

  private static Specification<Product> priceLesserOrEqualsThan(int maxPrice) {
    return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(
        root.get("price"), maxPrice);
  }

  private static Specification<Product> priceGreaterOrEqualsThan(int minPrice) {
    return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(
        root.get("price"), minPrice);
  }

  private static Specification<Product> titleContaining(String titlePart) {
    return (root, criteriaQuery, criteriaBuilder) ->
        criteriaBuilder.like(criteriaBuilder.lower(root.get("title")), String.format("%%%s%%", titlePart.toLowerCase()));
  }

  public static Specification<Product> build(MultiValueMap<String, String> params) {
    Specification<Product> spec = Specification.where(null);

    if (params.containsKey(FILTER_MIN_PRICE) && !params.get(FILTER_MIN_PRICE).get(0).isEmpty()) {
      spec = spec.and(ProductSpecifications.priceGreaterOrEqualsThan(
          Integer.parseInt(params.get(FILTER_MIN_PRICE).get(0))));
    }
    if (params.containsKey(FILTER_MAX_PRICE) && !params.get(FILTER_MAX_PRICE).get(0).isEmpty()) {
      spec = spec.and(ProductSpecifications.priceLesserOrEqualsThan(
          Integer.parseInt(params.get(FILTER_MAX_PRICE).get(0))));
    }
    if (params.containsKey(FILTER_TITLE) && !params.get(FILTER_TITLE).get(0).isEmpty()) {
      spec = spec.and(ProductSpecifications.titleContaining(params.get(FILTER_TITLE).get(0)));
    }
    return spec;
  }

}
