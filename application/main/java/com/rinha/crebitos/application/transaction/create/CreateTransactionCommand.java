package com.rinha.crebitos.application.transaction.create;

public record CreateTransactionCommand(
    int value,
    String type,
    String description) {

  public static CreateTransactionCommand with(
      int value,
      String type,
      String description) {
    return new CreateTransactionCommand(value, type, description);
  }
}
