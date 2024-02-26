package com.rinha.crebitos.application.transaction.create;

import com.rinha.crebitos.application.UseCaseTest;
import com.rinha.crebitos.domain.transaction.TransactionGateway;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.List;
import java.util.Objects;

import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

class CreateTransactionUseCaseTest extends UseCaseTest {
  @InjectMocks
  private DefaultCreateTransactionUseCase useCase;

  @Mock
  private TransactionGateway transactionGateway;

  @Override
  protected List<Object> getMocks() {
    return List.of(transactionGateway);
  }

  @Test
  void givenAValidCommand_whenCallsCreateTransaction_shouldReturnTransactionId() {
    final var expectedValue = 10000;
    final var expectedType = "c";
    final var expectedDescription = "test";

    final var aCommand = CreateTransactionCommand.with(
        expectedValue,
        expectedType,
        expectedDescription);

    when(transactionGateway.create(any()))
        .thenAnswer(returnsFirstArg());

    final var actualOutput = useCase.execute(aCommand).get();

    Assertions.assertNotNull(actualOutput);
    Assertions.assertNotNull(actualOutput.id());

    Mockito.verify(transactionGateway, times(1))
        .create(argThat(aTransaction -> Objects.equals(expectedValue,
            aTransaction.getValue())
            && Objects.equals(expectedType, aTransaction.getType())
            && Objects.equals(expectedDescription, aTransaction.getDescription())));

  }

  @Test
  void givenAInvalidType_whenCallsCreateTransaction_thenShouldReturnDomainException() {
    final var expectedValue = 10000;
    final String expectedType = null;
    final var expectedDescription = "test";
    final var expectedErrorMessage = "'type' should not be null";
    final var expectedErrorCount = 1;

    final var aCommand = CreateTransactionCommand.with(
        expectedValue,
        expectedType,
        expectedDescription);

    final var notification = useCase.execute(aCommand).getLeft();

    Assertions.assertEquals(expectedErrorCount, notification.getErrors().size());
    Assertions.assertEquals(expectedErrorMessage, notification.firstError().message());

    Mockito.verify(transactionGateway, times(0)).create(any());
  }

  @Test
  void givenAInvalidDescription_whenCallsCreateTransaction_thenShouldReturnDomainException() {
    final var expectedValue = 10000;
    final var expectedType = "c";
    final String expectedDescription = null;
    final var expectedErrorMessage = "'description' should not be null";
    final var expectedErrorCount = 1;

    final var aCommand = CreateTransactionCommand.with(
        expectedValue,
        expectedType,
        expectedDescription);

    final var notification = useCase.execute(aCommand).getLeft();

    Assertions.assertEquals(expectedErrorCount, notification.getErrors().size());
    Assertions.assertEquals(expectedErrorMessage,
        notification.firstError().message());

    Mockito.verify(transactionGateway, times(0)).create(any());
  }

  @Test
  void givenAValidCommand_whenGatewayThrowsRandomException_shouldReturnAException() {

    final var expectedValue = 10000;
    final var expectedType = "c";
    final var expectedDescription = "test";
    final var expectedErrorCount = 1;
    final var expectedErrorMessage = "Gateway error";

    final var aCommand = CreateTransactionCommand.with(
        expectedValue,
        expectedType,
        expectedDescription);

    when(transactionGateway.create(any()))
        .thenThrow(new IllegalStateException(expectedErrorMessage));

    final var notification = useCase.execute(aCommand).getLeft();

    Assertions.assertEquals(expectedErrorCount, notification.getErrors().size());
    Assertions.assertEquals(expectedErrorMessage,
        notification.firstError().message());

    Mockito.verify(transactionGateway, times(1))
        .create(argThat(aTransaction -> Objects.equals(expectedValue,
            aTransaction.getValue())
            && Objects.equals(expectedType, aTransaction.getType())
            && Objects.equals(expectedDescription, aTransaction.getDescription())));

  }
}
