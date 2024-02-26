package com.rinha.crebitos.domain.transaction;

import java.time.Instant;
import java.util.Objects;

import com.rinha.crebitos.domain.AggregateRoot;
import com.rinha.crebitos.domain.utils.InstantUtils;
import com.rinha.crebitos.domain.validation.ValidationHandler;

public class Transaction extends AggregateRoot<TransactionID> implements Cloneable {
  private int value;
  private String type;
  private String description;
  private Instant createdAt;

  public Transaction(final TransactionID anId, final int aValue, final String aType, final String aDescription,
      final Instant aCreationDate) {
    super(anId);
    this.value = aValue;
    this.createdAt = Objects.requireNonNull(aCreationDate, "'createdAt' should not be null");
    this.type = aType;
    this.description = aDescription;
  }

  public static Transaction newTransaction(final int aValue, final String aType, final String aDescription) {
    final var id = TransactionID.unique();
    final var now = InstantUtils.now();

    return new Transaction(id, aValue, aType, aDescription, now);
  }

  public static Transaction with(
      final TransactionID anId,
      final int aValue,
      final String aType,
      final String aDescription,
      final Instant createAt) {
    return new Transaction(anId, aValue, aType, aDescription, createAt);
  }

  public static Transaction with(final Transaction aTransaction) {
    return with(aTransaction.getId(), aTransaction.value, aTransaction.type, aTransaction.description,
        aTransaction.createdAt);
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

  public Instant getCreatedAt() {
    return createdAt;
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
