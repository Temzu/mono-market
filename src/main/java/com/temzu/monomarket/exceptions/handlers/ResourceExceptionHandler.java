package com.temzu.monomarket.exceptions.handlers;

import com.temzu.monomarket.exceptions.MarketError;
import com.temzu.monomarket.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ResourceExceptionHandler {

  @ExceptionHandler
  public ResponseEntity<?> catchResourceNotFoundException(ResourceNotFoundException e) {
    return new ResponseEntity<>(new MarketError(e.getMessage()), HttpStatus.NOT_FOUND);
  }
}
