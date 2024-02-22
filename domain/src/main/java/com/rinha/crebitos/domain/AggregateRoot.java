package com.rinha.crebitos.domain;

import java.util.List;

import com.rinha.crebitos.domain.events.DomainEvent;

public abstract class AggregateRoot<ID extends Identifier> extends Entity<ID> {

  protected AggregateRoot(final ID id) {
    super(id);
  }

  protected AggregateRoot(final ID id, final List<DomainEvent> events) {
    super(id, events);
  }
}