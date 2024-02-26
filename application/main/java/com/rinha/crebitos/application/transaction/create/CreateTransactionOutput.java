package com.rinha.crebitos.application.transaction.create;

import com.rinha.crebitos.domain.transaction.Transaction;

public record CreateTransactionOutput(
    String id) {
  public static CreateTransactionOutput from(final String anId) {
    return new CreateTransactionOutput(anId);
  }

  public static CreateTransactionOutput from(final Transaction aTransaction) {
    return new CreateTransactionOutput(aTransaction.getId().getValue());
  }
}
