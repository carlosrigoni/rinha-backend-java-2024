package com.rinha.crebitos.domain.transaction;

import com.rinha.crebitos.domain.UnitTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

class TransactionTest extends UnitTest {
  @Test
  void givenAValidParams_whenCallNewTransaction_thenInstantiateATransaction() {
    final var expectedValue = 1000;
    final var expectedType = "c";
    final var expectedDescription = "description";

    final var actualTransaction = new Transaction(expectedValue, expectedType, expectedDescription);

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

    final var actualTransaction1 = new Transaction(expectedValue, expectedType, expectedDescription);
    final var actualTransaction2 = new Transaction(expectedValue, expectedType, expectedDescription);

    Assertions.assertNotEquals(actualTransaction1.getId(), actualTransaction2.getId());
  }

  @Test
  void givenAValidParams_whenCallNewTransaction_thenInstantiateATransactionWithDifferentValues() {
    final var expectedType = "c";
    final var expectedDescription = "description";

    final var actualTransaction1 = new Transaction(1000, expectedType, expectedDescription);
    final var actualTransaction2 = new Transaction(2000, expectedType, expectedDescription);

    Assertions.assertNotEquals(actualTransaction1.getValue(), actualTransaction2.getValue());
  }

  @Test
  void givenAValidParams_whenCallNewTransaction_thenInstantiateATransactionWithDifferentTypes() {
    final var expectedValue = 1000;
    final var expectedDescription = "description";

    final var actualTransaction1 = new Transaction(expectedValue, "c", expectedDescription);
    final var actualTransaction2 = new Transaction(expectedValue, "d", expectedDescription);

    Assertions.assertNotEquals(actualTransaction1.getType(), actualTransaction2.getType());
  }

  @Test
  void givenAValidParams_whenCallNewTransaction_thenInstantiateATransactionWithDifferentDescriptions() {
    final var expectedValue = 1000;
    final var expectedType = "c";

    final var actualTransaction1 = new Transaction(expectedValue, expectedType, "description1");
    final var actualTransaction2 = new Transaction(expectedValue, expectedType, "description2");

    Assertions.assertNotEquals(actualTransaction1.getDescription(), actualTransaction2.getDescription());
  }
}