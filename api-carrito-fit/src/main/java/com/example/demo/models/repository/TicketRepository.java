package com.example.demo.models.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.demo.models.entity.Ticket;


public interface TicketRepository  extends CrudRepository<Ticket, Long>{
	 
	@Query(value = "SELECT * FROM TICKETS WHERE USUARIO_ID = ?1", nativeQuery = true)
	public Iterable<Ticket> findTicketsByUsuarioId(Long id);
}
