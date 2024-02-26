package com.rinha.crebitos.application.transaction.retrieve.list;

import com.rinha.crebitos.application.UseCase;
import com.rinha.crebitos.domain.pagination.Pagination;
import com.rinha.crebitos.domain.pagination.SearchQuery;

public abstract class ListTransactionsUseCase
    extends UseCase<SearchQuery, Pagination<TransactionListOutput>> {
}
