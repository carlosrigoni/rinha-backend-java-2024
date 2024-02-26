package com.rinha.crebitos.application.transaction.retrieve.list;

import java.time.Instant;

import com.rinha.crebitos.domain.transaction.Transaction;
import com.rinha.crebitos.domain.transaction.TransactionID;

public record TransactionListOutput(
    TransactionID id,
    int value,
    String type,
    String description,
    Instant createdAt) {

  public static TransactionListOutput from(final Transaction aTransaction) {
    return new TransactionListOutput(
        aTransaction.getId(),
        aTransaction.getValue(),
        aTransaction.getType(),
        aTransaction.getDescription(),
        aTransaction.getCreatedAt());
  }
}
