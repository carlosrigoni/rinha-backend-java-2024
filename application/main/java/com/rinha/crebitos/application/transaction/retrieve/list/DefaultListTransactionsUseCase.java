package com.rinha.crebitos.application.transaction.retrieve.list;

import java.util.Objects;

import com.rinha.crebitos.domain.pagination.Pagination;
import com.rinha.crebitos.domain.pagination.SearchQuery;
import com.rinha.crebitos.domain.transaction.TransactionGateway;

public class DefaultListTransactionsUseCase extends ListTransactionsUseCase {

  private final TransactionGateway transactionGateway;

  public DefaultListTransactionsUseCase(TransactionGateway transactionGateway) {
    this.transactionGateway = Objects.requireNonNull(transactionGateway);
  }

  @Override
  public Pagination<TransactionListOutput> execute(final SearchQuery aQuery) {
    return this.transactionGateway.findAll(aQuery).map(TransactionListOutput::from);
  }
}
