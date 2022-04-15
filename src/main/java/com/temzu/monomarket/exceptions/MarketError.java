package com.temzu.monomarket.exceptions;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MarketError {

  private List<String> messages;
  private LocalDateTime timestamp;

  public MarketError(String message) {
    this(List.of(message));
  }

  public MarketError(String... messages) {
    this(List.of(messages));
  }

  public MarketError(List<String> messages) {
    this.messages = new ArrayList<>(messages);
    this.timestamp = LocalDateTime.now();
  }
}
