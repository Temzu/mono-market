package com.temzu.monomarket.models;

import com.temzu.monomarket.dtos.OrderCreateDto;
import com.temzu.monomarket.dtos.OrderItemDto;
import com.temzu.monomarket.exceptions.ResourceNotFoundException;
import com.temzu.monomarket.util.Cart;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@NoArgsConstructor
@Data
@Entity
@Table(name = "orders")
public class Order {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @OneToMany(mappedBy = "order")
  @Cascade(org.hibernate.annotations.CascadeType.ALL)
  private List<OrderItem> items;

  @Column(name = "price")
  private BigDecimal price;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;

  @Column(name = "address")
  private String address;

  @Column(name = "phone")
  private String phone;

  @CreationTimestamp
  @Column(name = "created_at")
  private LocalDateTime createdAt;

  @UpdateTimestamp
  @Column(name = "updated_at")
  private LocalDateTime updatedAt;

  public Order(Cart cart, User curUser, OrderCreateDto orderCreateDto) {
    this.items = new ArrayList<>();
    this.user = curUser;
    this.address = orderCreateDto.getAddress();
    this.phone = orderCreateDto.getPhone();
    this.price = cart.getPrice();
    //    cart.getItems().forEach(ci -> {
    //      OrderItem oi = new OrderItem(ci);
    //      oi.setOrder(this);
    //      this.items.add(oi);
    //    });
    //    cart.getItems().forEach(oid -> {
    //      oid.set
    //      th
    //    });
    //    for (OrderItemDto o : cart.getItems()) {
    //      OrderItem orderItem = new OrderItem();
    //      orderItem.setOrder(order);
    //      orderItem.setQuantity(o.getQuantity());
    //      Product product = productService.findById(o.getProductId()).orElseThrow(() -> new
    // ResourceNotFoundException("Product not found"));
    //      orderItem.setPrice(product.getPrice().multiply(BigDecimal.valueOf(o.getQuantity())));
    //      orderItem.setPricePerProduct(product.getPrice());
    //      orderItem.setProduct(product);
    //      order.getItems().add(orderItem);
    //    }
  }
}
