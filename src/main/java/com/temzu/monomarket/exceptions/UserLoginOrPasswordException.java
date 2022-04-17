package com.temzu.monomarket.exceptions;

public class UserLoginOrPasswordException extends RuntimeException {

  private static final String WRONG_LOGIN_PASSWORD = "Wrong login or password entered";

  public UserLoginOrPasswordException() {
    super(WRONG_LOGIN_PASSWORD);
  }
}
