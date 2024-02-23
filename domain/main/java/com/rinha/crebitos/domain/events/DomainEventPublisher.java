package com.rinha.crebitos.domain.events;

@FunctionalInterface
public interface DomainEventPublisher {
  void publishEvent(DomainEvent event);
}
