package com.rinha.crebitos.domain.transaction;

import com.rinha.crebitos.domain.AggregateRoot;
import com.rinha.crebitos.domain.validation.ValidationHandler;

public class Transaction extends AggregateRoot<TransactionID> implements Cloneable {
  private int value;
  private String type;
  private String description;

  public Transaction(final TransactionID anId, final int aValue, final String aType, final String aDescription) {
    super(anId);
    this.value = aValue;
    this.type = aType;
    this.description = aDescription;
  }

  public static Transaction newTransaction(final int aValue, final String aType, final String aDescription) {
    final var id = TransactionID.unique();

    return new Transaction(id, aValue, aType, aDescription);
  }

  public static Transaction with(
      final TransactionID anId,
      final int aValue,
      final String aType,
      final String aDescription) {
    return new Transaction(anId, aValue, aType, aDescription);
  }

  public static Transaction with(final Transaction aTransaction) {
    return with(aTransaction.getId(), aTransaction.value, aTransaction.type, aTransaction.description);
  }

  @Override
  public void validate(final ValidationHandler handler) {
    new TransactionValidator(this, handler).validate();
  }

  public Transaction update(final int aValue, final String aType, final String aDescription) {
    this.value = aValue;
    this.type = aType;
    this.description = aDescription;

    return this;
  }

  public TransactionID getId() {
    return id;
  }

  public int getValue() {
    return value;
  }

  public void setValue(int value) {
    this.value = value;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  @Override
  public Transaction clone() {

    try {
      return (Transaction) super.clone();
    } catch (Exception e) {
      throw new AssertionError();
    }
  }
}
