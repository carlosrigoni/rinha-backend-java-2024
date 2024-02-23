package com.rinha.crebitos.domain.transaction;

public enum TransactionType {
  CREDIT("c"),
  DEBIT("d");

  private String type;

  TransactionType(String type) {
    this.type = type;
  }

  public String getType() {
    return type;
  }
}