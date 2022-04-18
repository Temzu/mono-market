package com.temzu.monomarket.models;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserInfo {

  private Long userId;
  private String userLogin;
  private String userEmail;
  private List<String> roles;
}
