package com.rinha.crebitos.application.transaction.retrive.list;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.rinha.crebitos.application.UseCaseTest;
import com.rinha.crebitos.application.transaction.retrieve.list.DefaultListTransactionsUseCase;
import com.rinha.crebitos.application.transaction.retrieve.list.TransactionListOutput;
import com.rinha.crebitos.domain.pagination.Pagination;
import com.rinha.crebitos.domain.pagination.SearchQuery;
import com.rinha.crebitos.domain.transaction.Transaction;
import com.rinha.crebitos.domain.transaction.TransactionGateway;

class ListTransactionsUseCaseTest extends UseCaseTest {

  @InjectMocks
  private DefaultListTransactionsUseCase useCase;

  @Mock
  private TransactionGateway transactionGateway;

  @Override
  protected List<Object> getMocks() {
    return List.of(transactionGateway);
  }

  @Test
  void givenAValidQuery_whenCallsListTransactions_thenShouldReturnTransactions() {
    final var transactions = List.of(
        Transaction.newTransaction(1000, "c", "test"),
        Transaction.newTransaction(100, "d", "test"));

    final var expectedPage = 0;
    final var expectedPerPage = 10;
    final var expectedTerms = "";
    final var expectedSort = "createdAt";
    final var expectedDirection = "asc";

    final var aQuery = new SearchQuery(expectedPage, expectedPerPage, expectedTerms, expectedSort, expectedDirection);

    final var expectedPagination = new Pagination<>(expectedPage, expectedPerPage, transactions.size(), transactions);

    final var expectedItemsCount = 2;
    final var expectedResult = expectedPagination.map(TransactionListOutput::from);

    when(transactionGateway.findAll(eq(aQuery))).thenReturn(expectedPagination);

    final var actualResult = useCase.execute(aQuery);

    Assertions.assertEquals(expectedItemsCount, actualResult.items().size());
    Assertions.assertEquals(expectedResult, actualResult);
    Assertions.assertEquals(expectedPage, actualResult.currentPage());
    Assertions.assertEquals(expectedPerPage, actualResult.perPage());
    Assertions.assertEquals(transactions.size(), actualResult.total());
  }

  @Test
  void givenAValidQuery_whenHasNoResults_thenShouldReturnEmptyCategories() {
    final var categories = List.<Transaction>of();

    final var expectedPage = 0;
    final var expectedPerPage = 10;
    final var expectedTerms = "";
    final var expectedSort = "createdAt";
    final var expectedDirection = "asc";

    final var aQuery = new SearchQuery(expectedPage, expectedPerPage, expectedTerms, expectedSort, expectedDirection);

    final var expectedPagination = new Pagination<>(expectedPage, expectedPerPage, categories.size(), categories);

    final var expectedItemsCount = 0;
    final var expectedResult = expectedPagination.map(TransactionListOutput::from);

    when(transactionGateway.findAll(eq(aQuery)))
        .thenReturn(expectedPagination);

    final var actualResult = useCase.execute(aQuery);

    Assertions.assertEquals(expectedItemsCount, actualResult.items().size());
    Assertions.assertEquals(expectedResult, actualResult);
    Assertions.assertEquals(expectedPage, actualResult.currentPage());
    Assertions.assertEquals(expectedPerPage, actualResult.perPage());
    Assertions.assertEquals(categories.size(), actualResult.total());

  }

  @Test
  void givenAValidQuery_whenGatewayThrowsException_shouldReturnException() {
    final var expectedPage = 0;
    final var expectedPerPage = 10;
    final var expectedTerms = "";
    final var expectedSort = "createdAt";
    final var expectedDirection = "asc";
    final var expectedErrorMessage = "Gateway error";

    final var aQuery = new SearchQuery(expectedPage, expectedPerPage, expectedTerms, expectedSort, expectedDirection);

    when(transactionGateway.findAll(eq(aQuery)))
        .thenThrow(new IllegalStateException(expectedErrorMessage));

    final var actualException = Assertions.assertThrows(IllegalStateException.class, () -> useCase.execute(aQuery));

    Assertions.assertEquals(expectedErrorMessage, actualException.getMessage());
  }
}
