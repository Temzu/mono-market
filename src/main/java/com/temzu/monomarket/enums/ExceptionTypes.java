package com.temzu.monomarket.enums;

public enum ExceptionTypes {
  ID("ID"),
  LOGIN("LOGIN"),
  NAME("NAME"),
  TITLE("TITLE"),
  UUID("UUID"),
  EMAIL("EMAIL"),
  PASSWORD("PASSWORD");

  private final String type;

  ExceptionTypes(String type) {
    this.type = type;
  }

  public String getType() {
    return type;
  }
}
