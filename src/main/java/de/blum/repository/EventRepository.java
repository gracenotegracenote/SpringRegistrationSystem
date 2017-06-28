package de.blum.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import de.blum.domain.Event;

@Repository
public interface EventRepository extends CrudRepository<Event, Integer> {
}
