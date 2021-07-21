package com.example.demo.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.entity.Producto;
import com.example.demo.models.repository.ProductoRepository;

@Service
public class ProductoServiceImpl implements ProductoService{

	@Autowired
	protected ProductoRepository repository;

	@Override
	public Producto save(Producto producto) {
		return repository.save(producto);
	}

	@Override
	public Iterable<Producto> findAll() {
		return repository.findAll();
	}

	@Override
	public Optional<Producto> findById(Long id) {
		return repository.findById(id);
	}

}
