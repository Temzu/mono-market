package com.temzu.monomarket.exceptions;

public class TokenExpiredException extends RuntimeException{

  private static final String TOKEN_EXPIRED = "Token expired: %s";

  public TokenExpiredException(String token) {
    super(String.format(TOKEN_EXPIRED, token));
  }
}
