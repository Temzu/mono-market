package com.temzu.monomarket.controllers;

import com.temzu.monomarket.dtos.AuthRequestDto;
import com.temzu.monomarket.dtos.AuthResponseDto;
import com.temzu.monomarket.dtos.SignUpRequestDto;
import com.temzu.monomarket.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

  private final AuthService authService;

  @PostMapping("/signup")
  public AuthResponseDto signUp(@RequestBody SignUpRequestDto signUpRequest) {
    return authService.signUp(signUpRequest);
  }

  @PostMapping("/login")
  public AuthResponseDto login(@RequestBody AuthRequestDto request) {
    return authService.login(request);
  }
}
