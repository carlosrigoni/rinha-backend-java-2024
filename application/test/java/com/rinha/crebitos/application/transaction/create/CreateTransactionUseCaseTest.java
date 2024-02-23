package com.rinha.crebitos.application.transaction.create;

import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Objects;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

import com.rinha.crebitos.application.UseCaseTest;
import com.rinha.crebitos.domain.transaction.TransactionGateway;

class CreateTransactionUseCaseTest extends UseCaseTest {
  @InjectMocks
  private DefaultCreateTransactionUseCase useCase;

  @Mock
  private TransactionGateway transactionGateway;

  @Override
  protected List<Object> getMocks() {
    return List.of(transactionGateway);
  }

  // 1. Teste do caminho feliz
  // 2. Teste passando uma propriedade inválida (value, type, description)
  // 4. Teste simulando um erro generico vindo do gateway

  @Test
  void givenAvalidCommand_whenCallsCreateTransaction_shouldReturnTransactionId() {
    final var expectedValue = 10000;
    final var expectedType = "c";
    final var expectedDescription = "description";

    final var aCommand = CreateTransactionCommand.with(
        expectedValue,
        expectedType,
        expectedDescription);

    when(transactionGateway.create(any()))
        .thenAnswer(returnFirstArg());

    final var actualOutput = useCase.execute(aCommand).get();

    Assertions.assertNotNull(actualOutput);
    Assertions.assertNotNull(actualOutput.id());

    Mockito.verify(transactionGateway, times(1))
        .create(argThat(aTransaction -> Objects.equals(expectedValue, aTransaction.getValue())
            && Objects.equals(expectedType, aTransaction.getType())
            && Objects.equals(expectedDescription, aTransaction.getDescription())));

  }

  void givenAInvalidValue_whenCallsCreateTransaction_thenShouldReturnDomainException() {
    final String expectedValue = null;
    final var expectedType = "c";
    final var expectedDescription = "description";
    final var expectedErrorMessage = "'value' should not be null";
    final var expectedErrorCount = 1;

    final var aCommand = CreateTransactionCommand.with(
        expectedValue,
        expectedType,
        expectedDescription);

    final var notification = useCase.execute(aCommand).getNotification();

    Assertions.assertEquals(expectedErrorCount, notification.getErrors().size());
    Assertions.assertEquals(expectedErrorMessage, notification.firstError().message());

    Mockito.verify(transactionGateway, times(0)).create(any());

  }

}
