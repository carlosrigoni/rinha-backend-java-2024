package com.rinha.crebitos.domain.transaction;

import com.rinha.crebitos.domain.UnitTest;
import com.rinha.crebitos.domain.exceptions.DomainException;
import com.rinha.crebitos.domain.validation.ThrowsValidationHandler;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

class TransactionTest extends UnitTest {
  @Test
  void givenAValidParams_whenCallNewTransaction_thenInstantiateATransaction() {
    final var expectedValue = 1000;
    final var expectedType = "c";
    final var expectedDescription = "description";

    final var actualTransaction = Transaction.newTransaction(expectedValue, expectedType, expectedDescription);

    Assertions.assertNotNull(actualTransaction);
    Assertions.assertNotNull(actualTransaction.getId());
    Assertions.assertEquals(expectedValue, actualTransaction.getValue());
    Assertions.assertEquals(expectedType, actualTransaction.getType());
    Assertions.assertEquals(expectedDescription, actualTransaction.getDescription());
  }

  @Test
  void givenAValidParams_whenCallNewTransaction_thenInstantiateATransactionWithDifferentIds() {
    final var expectedValue = 1000;
    final var expectedType = "c";
    final var expectedDescription = "description";

    final var actualTransaction1 = Transaction.newTransaction(expectedValue, expectedType, expectedDescription);
    final var actualTransaction2 = Transaction.newTransaction(expectedValue, expectedType, expectedDescription);

    Assertions.assertNotEquals(actualTransaction1.getId(), actualTransaction2.getId());
  }

  @Test
  void givenAValidParams_whenCallNewTransaction_thenInstantiateATransactionWithDifferentValues() {
    final var expectedType = "c";
    final var expectedDescription = "description";

    final var actualTransaction1 = Transaction.newTransaction(1000, expectedType, expectedDescription);
    final var actualTransaction2 = Transaction.newTransaction(2000, expectedType, expectedDescription);

    Assertions.assertNotEquals(actualTransaction1.getValue(), actualTransaction2.getValue());
  }

  @Test
  void givenAValidParams_whenCallNewTransaction_thenInstantiateATransactionWithDifferentTypes() {
    final var expectedValue = 1000;
    final var expectedDescription = "description";

    final var actualTransaction1 = Transaction.newTransaction(expectedValue, "c", expectedDescription);
    final var actualTransaction2 = Transaction.newTransaction(expectedValue, "d", expectedDescription);

    Assertions.assertNotEquals(actualTransaction1.getType(), actualTransaction2.getType());
  }

  @Test
  void givenAValidParams_whenCallNewTransaction_thenInstantiateATransactionWithDifferentDescriptions() {
    final var expectedValue = 1000;
    final var expectedType = "c";

    final var actualTransaction1 = Transaction.newTransaction(expectedValue, expectedType, "description1");
    final var actualTransaction2 = Transaction.newTransaction(expectedValue, expectedType, "description2");

    Assertions.assertNotEquals(actualTransaction1.getDescription(), actualTransaction2.getDescription());
  }

  @Test
  void givenAnInvalidType_whenCallNewTransactionAndValidate_thenShouldReceiveError() {
    final var expectedValue = 1000;
    final var expectedType = "invalid";
    final var expectedDescription = "description";
    final var expectedErrorMessage = "'type' must be 'c' or 'd'";
    final var expectedErrorCount = 1;

    final var actualTransaction = Transaction.newTransaction(expectedValue, expectedType, expectedDescription);

    final var actualException = Assertions.assertThrows(DomainException.class,
        () -> actualTransaction.validate(new ThrowsValidationHandler()));

    Assertions.assertEquals(expectedErrorCount, actualException.getErrors().size());
    Assertions.assertEquals(expectedErrorMessage, actualException.getErrors().get(0).message());

  }

  @Test
  void givenAnInvalidNullType_whenCallNewTransactionAndValidate_thenShouldReceiveError()
      throws DomainException {
    final var expectedValue = 1000;
    final String expectedType = null;
    final var expectedDescription = "description";
    final var expectedErrorMessage = "'type' should not be null";
    final var expectedErrorCount = 1;

    final var actualTransaction = Transaction.newTransaction(expectedValue, expectedType, expectedDescription);

    final var actualException = Assertions.assertThrows(DomainException.class,
        () -> actualTransaction.validate(new ThrowsValidationHandler()));

    Assertions.assertEquals(expectedErrorCount, actualException.getErrors().size());
    Assertions.assertEquals(expectedErrorMessage, actualException.getErrors().get(0).message());
  }

  @Test
  void givenAnInvalidBlankType_whenCallNewTransactionAndValidate_thenShouldReceiveError()
      throws DomainException {
    final var expectedValue = 1000;
    final var expectedType = " ";
    final var expectedDescription = "description";
    final var expectedErrorMessage = "'type' should not be empty";
    final var expectedErrorCount = 1;

    final var actualTransaction = Transaction.newTransaction(expectedValue, expectedType, expectedDescription);

    final var actualException = Assertions.assertThrows(DomainException.class,
        () -> actualTransaction.validate(new ThrowsValidationHandler()));

    Assertions.assertEquals(expectedErrorCount, actualException.getErrors().size());
    Assertions.assertEquals(expectedErrorMessage, actualException.getErrors().get(0).message());
  }

  @Test
  void givenAnInvalidLongerDescription_whenCallNewTransactionAndValidate_thenShouldReceiveError() {
    final var expectedValue = 1000;
    final var expectedType = "c";
    final var expectedDescription = "descriptiondescription";
    final var expectedErrorMessage = "'description' must be between 1 and 10 characters";
    final var expectedErrorCount = 1;

    final var actualTransaction = Transaction.newTransaction(expectedValue, expectedType, expectedDescription);

    final var actualException = Assertions.assertThrows(DomainException.class,
        () -> actualTransaction.validate(new ThrowsValidationHandler()));

    Assertions.assertEquals(expectedErrorCount, actualException.getErrors().size());
    Assertions.assertEquals(expectedErrorMessage, actualException.getErrors().get(0).message());
  }

  @Test
  void givenAnInvalidShorterDescription_whenCallNewTransactionAndValidate_thenShouldReceiveError() {
    final var expectedValue = 1000;
    final var expectedType = "c";
    final var expectedDescription = "";
    final var expectedErrorMessage = "'description' should not be empty";
    final var expectedErrorCount = 1;

    final var actualTransaction = Transaction.newTransaction(expectedValue, expectedType, expectedDescription);

    final var actualException = Assertions.assertThrows(DomainException.class,
        () -> actualTransaction.validate(new ThrowsValidationHandler()));

    Assertions.assertEquals(expectedErrorCount, actualException.getErrors().size());
    Assertions.assertEquals(expectedErrorMessage, actualException.getErrors().get(0).message());
  }

  @Test
  void givenAnInvalidNullDescription_whenCallNewTransactionAndValidate_thenShouldReceiveError() {
    final var expectedValue = 1000;
    final var expectedType = "c";
    final String expectedDescription = null;
    final var expectedErrorMessage = "'description' should not be null";
    final var expectedErrorCount = 1;

    final var actualTransaction = Transaction.newTransaction(expectedValue, expectedType, expectedDescription);

    final var actualException = Assertions.assertThrows(DomainException.class,
        () -> actualTransaction.validate(new ThrowsValidationHandler()));

    Assertions.assertEquals(expectedErrorCount, actualException.getErrors().size());
    Assertions.assertEquals(expectedErrorMessage, actualException.getErrors().get(0).message());
  }

  @Test
  void givenAnInvalidBlankDescription_whenCallNewTransactionAndValidate_thenShouldReceiveError() {
    final var expectedValue = 1000;
    final var expectedType = "c";
    final var expectedDescription = " ";
    final var expectedErrorMessage = "'description' should not be empty";
    final var expectedErrorCount = 1;

    final var actualTransaction = Transaction.newTransaction(expectedValue, expectedType, expectedDescription);

    final var actualException = Assertions.assertThrows(DomainException.class,
        () -> actualTransaction.validate(new ThrowsValidationHandler()));

    Assertions.assertEquals(expectedErrorCount, actualException.getErrors().size());
    Assertions.assertEquals(expectedErrorMessage, actualException.getErrors().get(0).message());
  }

  @Test
  void givenAValidParams_whenCallUpdate_thenShouldUpdateTransaction() {
    final var expectedValue = 1000;
    final var expectedType = "c";
    final var expectedDescription = "description";
    final var actualTransaction = Transaction.newTransaction(expectedValue, expectedType, expectedDescription);
    final var expectedNewValue = 2000;
    final var expectedNewType = "d";
    final var expectedNewDescription = "new description";

    final var actualUpdatedTransaction = actualTransaction.update(expectedNewValue, expectedNewType,
        expectedNewDescription);

    Assertions.assertEquals(expectedNewValue, actualUpdatedTransaction.getValue());
    Assertions.assertEquals(expectedNewType, actualUpdatedTransaction.getType());
    Assertions.assertEquals(expectedNewDescription, actualUpdatedTransaction.getDescription());
  }

  @Test
  void givenAValidTransaction_whenCallUpdateWithInvalidParams_thenReturnTransactionUpdated() {
    final var expectedValue = 1000;
    final var expectedType = "c";
    final var expectedDescription = "description";
    final var actualTransaction = Transaction.newTransaction(expectedValue, expectedType, expectedDescription);
    final var expectedNewValue = 2000;
    final var expectedNewType = "d";
    final var expectedNewDescription = "new description";

    final var actualUpdatedTransaction = actualTransaction.update(expectedNewValue, expectedNewType,
        expectedNewDescription);

    Assertions.assertEquals(expectedNewValue, actualUpdatedTransaction.getValue());
    Assertions.assertEquals(expectedNewType, actualUpdatedTransaction.getType());
    Assertions.assertEquals(expectedNewDescription, actualUpdatedTransaction.getDescription());
  }
}