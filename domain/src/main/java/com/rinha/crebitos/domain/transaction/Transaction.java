package com.rinha.crebitos.domain.transaction;

import java.util.UUID;

public class Transaction {
  private String id;
  private int value;
  private String type;
  private String description;

  public Transaction(int value, String type, String description) {
    this.id = UUID.randomUUID().toString();
    this.value = value;
    this.type = type;
    this.description = description;
  }

  public String getId() {
    return id;
  }

  public int getValue() {
    return value;
  }

  public String getType() {
    return type;
  }

  public String getDescription() {
    return description;
  }
}
