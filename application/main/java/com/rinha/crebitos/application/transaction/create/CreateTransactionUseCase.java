package com.rinha.crebitos.application.transaction.create;

import com.rinha.crebitos.application.UseCase;
import com.rinha.crebitos.domain.validation.handler.Notification;
import io.vavr.control.Either;

public abstract class CreateTransactionUseCase
    extends UseCase<CreateTransactionCommand, Either<Notification, CreateTransactionOutput>> {
}
