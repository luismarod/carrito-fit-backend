package com.example.demo.controllers;

import java.io.IOException;
import java.util.Date;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.models.entity.Producto;
import com.example.demo.models.entity.Ticket;
import com.example.demo.models.entity.Usuario;
import com.example.demo.services.TicketService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/tickets")
public class TicketController extends UsuarioController{
	
	@Autowired
	protected TicketService ticketService;
	
	@GetMapping("/obtener-tickets")
	public ResponseEntity<?> listarTickets() {
		return ResponseEntity.ok(ticketService.findAll());
	}
	
	@GetMapping("/obtener-ticket-usuario/{id}")
	public ResponseEntity<?> ticketsPorUsuarioId(@PathVariable Long id) {
		return ResponseEntity.ok().body(ticketService.findTicketsByUsuarioId(id));
	}

	@PostMapping("/crear-ticket")
	public ResponseEntity<?> crearTicket(@RequestBody Ticket ticket) {
		
		Usuario comprador = ticket.getUsuario();
		
		Optional o = usuarioService.findById(comprador.getId());
		
		if(!o.isEmpty() && ticket.getMonto()> 10000) {
			comprador.setFechaCambioCategoria(new Date());
			comprador.setVip(true);
		}
		comprador.setUltimaCompra(new Date());
		usuarioService.save(comprador);
		ticketService.save(ticket);

		return ResponseEntity.status(HttpStatus.CREATED).body(ticket);
	}
}
