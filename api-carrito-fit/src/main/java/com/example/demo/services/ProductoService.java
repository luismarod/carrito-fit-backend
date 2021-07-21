package com.example.demo.services;

import java.util.Optional;

import com.example.demo.models.entity.Producto;
import com.example.demo.models.entity.Usuario;

public interface ProductoService {
	
	public Iterable<Producto> findAll();
	
	public Optional<Producto> findById(Long id);

	public Producto save(Producto producto);

}
