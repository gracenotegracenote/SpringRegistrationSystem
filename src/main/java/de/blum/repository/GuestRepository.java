package de.blum.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

import de.blum.domain.Guest;

@Repository
public interface GuestRepository extends CrudRepository<Guest, Integer> {

	public Guest findByName(String name);

	public Guest findByEmail(String email);

	public List<Guest> findAll();
}
