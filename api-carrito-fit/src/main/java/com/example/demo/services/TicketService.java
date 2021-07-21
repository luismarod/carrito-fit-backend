package com.example.demo.services;

import com.example.demo.models.entity.Producto;
import com.example.demo.models.entity.Ticket;

public interface TicketService {
	
	public Iterable<Ticket> findAll();

	public Ticket save(Ticket ticket);
	
	public Iterable<Ticket> findTicketsByUsuarioId(Long id);
}
