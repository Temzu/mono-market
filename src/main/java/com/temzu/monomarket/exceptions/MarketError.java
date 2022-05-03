package com.temzu.monomarket.exceptions;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
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

  @JsonSerialize(using = LocalDateTimeSerializer.class)
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  @JsonProperty("date")
  public LocalDateTime getTimestamp() {
    return timestamp;
  }
}
