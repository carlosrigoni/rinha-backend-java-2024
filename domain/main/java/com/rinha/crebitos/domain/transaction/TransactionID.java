package com.rinha.crebitos.domain.transaction;

import java.util.Objects;

import com.rinha.crebitos.domain.Identifier;
import com.rinha.crebitos.domain.utils.IdUtils;

public class TransactionID extends Identifier {
  private final String value;

  private TransactionID(final String value) {
    this.value = Objects.requireNonNull(value);
  }

  public static TransactionID unique() {
    return TransactionID.from(IdUtils.uuid());
  }

  public static TransactionID from(final String value) {
    return new TransactionID(value);
  }

  @Override
  public String getValue() {
    return value;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    final TransactionID that = (TransactionID) o;
    return getValue().equals(that.getValue());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getValue());
  }
}
