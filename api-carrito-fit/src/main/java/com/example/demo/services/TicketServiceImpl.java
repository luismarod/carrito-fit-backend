package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.entity.Producto;
import com.example.demo.models.entity.Ticket;
import com.example.demo.models.repository.TicketRepository;

@Service
public class TicketServiceImpl implements TicketService {

	@Autowired
	protected TicketRepository ticketRepository;
	
	@Override
	public Iterable<Ticket> findAll() {
		return ticketRepository.findAll();
	}

	@Override
	public Ticket save(Ticket ticket) {
		return ticketRepository.save(ticket);
	}

	@Override
	public Iterable<Ticket> findTicketsByUsuarioId(Long id) {
		return ticketRepository.findTicketsByUsuarioId(id);
	}

}
