package com.rinha.crebitos.domain.transaction;

import com.rinha.crebitos.domain.validation.Error;
import com.rinha.crebitos.domain.validation.ValidationHandler;
import com.rinha.crebitos.domain.validation.Validator;

public class TransactionValidator extends Validator {

  public static final int DESCRIPTION_MAX_LENGTH = 10;
  public static final int DESCRIPTION_MIN_LENGTH = 1;
  private final Transaction transaction;

  public TransactionValidator(final Transaction aTransaction, final ValidationHandler aHandler) {
    super(aHandler);
    this.transaction = aTransaction;
  }

  @Override
  public void validate() {
    checkTypeConstraints();
    checkDescriptionConstraints();
  }

  private void checkTypeConstraints() {
    final var type = this.transaction.getType();

    if (type == null) {
      this.validationHandler().append(new Error("'type' should not be null"));
      return;
    }

    if (type.isBlank()) {
      this.validationHandler().append(new Error("'type' should not be empty"));
      return;
    }

    if (!type.equals("c") && !type.equals("d")) {
      this.validationHandler().append(new Error("'type' must be 'c' or 'd'"));
    }

  }

  private void checkDescriptionConstraints() {
    final var description = this.transaction.getDescription();

    if (description == null) {
      this.validationHandler().append(new Error("'description' should not be null"));
      return;
    }

    if (description.isBlank()) {
      this.validationHandler().append(new Error("'description' should not be empty"));
      return;
    }

    final int length = description.trim().length();
    if (length > DESCRIPTION_MAX_LENGTH || length < DESCRIPTION_MIN_LENGTH) {
      this.validationHandler().append(new Error("'description' must be between 1 and 10 characters"));
    }
  }
}
