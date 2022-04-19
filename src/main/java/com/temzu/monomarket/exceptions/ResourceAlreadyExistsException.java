package com.temzu.monomarket.exceptions;

import com.temzu.monomarket.enums.ExceptionTypes;
import java.util.UUID;

public class ResourceAlreadyExistsException extends ResourceException {

  private static final String ALREADY_EXISTS_BY = "%s already exists with [%s]: %s";

  private ResourceAlreadyExistsException(Class<?> entityClass, ExceptionTypes type, String reason) {
    super(String.format(ALREADY_EXISTS_BY, entityClass.getSimpleName(), type.getType(), reason));
  }

  public static ResourceAlreadyExistsException byLogin(String login, Class<?> entityClass) {
    return new ResourceAlreadyExistsException(entityClass, ExceptionTypes.LOGIN, login);
  }

  public static ResourceAlreadyExistsException byUuid(UUID uuid, Class<?> entityClass) {
    return new ResourceAlreadyExistsException(entityClass, ExceptionTypes.UUID, uuid.toString());
  }

  public static ResourceAlreadyExistsException byEmail(String email, Class<?> entityClass) {
    return new ResourceAlreadyExistsException(entityClass, ExceptionTypes.EMAIL, email);
  }
}
