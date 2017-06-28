package de.blum.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import de.blum.domain.Owner;

@Repository
public interface OwnerRepository extends CrudRepository<Owner, Integer> {
}
