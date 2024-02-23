package com.rinha.crebitos.domain.transaction;

import com.rinha.crebitos.domain.pagination.Pagination;
import com.rinha.crebitos.domain.pagination.SearchQuery;

public interface TransactionGateway {

  Transaction create(Transaction aTransaction);

  Pagination<Transaction> findAll(SearchQuery aQuery);
}
