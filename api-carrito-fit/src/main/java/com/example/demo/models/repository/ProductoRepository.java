package com.example.demo.models.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.models.entity.Producto;

public interface ProductoRepository extends CrudRepository<Producto, Long>{

}
