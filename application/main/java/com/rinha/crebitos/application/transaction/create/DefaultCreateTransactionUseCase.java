package com.rinha.crebitos.application.transaction.create;

import com.rinha.crebitos.domain.transaction.Transaction;
import com.rinha.crebitos.domain.transaction.TransactionGateway;
import com.rinha.crebitos.domain.validation.handler.Notification;
import io.vavr.control.Either;

import java.util.Objects;

import static io.vavr.API.Left;
import static io.vavr.API.Try;

public class DefaultCreateTransactionUseCase extends CreateTransactionUseCase {

  private final TransactionGateway transactionGateway;

  public DefaultCreateTransactionUseCase(final TransactionGateway transactionGateway) {
    this.transactionGateway = Objects.requireNonNull(transactionGateway);
  }

  @Override
  public Either<Notification, CreateTransactionOutput> execute(final CreateTransactionCommand aCommand) {
    final var aValue = aCommand.value();
    final var aType = aCommand.type();
    final var aDescription = aCommand.description();

    final var notification = Notification.create();

    final var aTransaction = Transaction.newTransaction(aValue, aType, aDescription);
    aTransaction.validate(notification);

    return notification.hasError() ? Left(notification) : create(aTransaction);
  }

  private Either<Notification, CreateTransactionOutput> create(final Transaction aTransaction) {
    return Try(() -> this.transactionGateway.create(aTransaction))
        .toEither()
        .bimap(Notification::create, CreateTransactionOutput::from);
  }
}
