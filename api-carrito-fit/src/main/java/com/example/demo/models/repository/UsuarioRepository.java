package com.example.demo.models.repository;

import java.util.Date;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.demo.models.entity.Usuario;

public interface UsuarioRepository extends CrudRepository<Usuario, Long>{
	public Usuario findUsuarioByUsername(String username);
	
	@Query(value = "SELECT * FROM USUARIOS WHERE VIP = true", nativeQuery = true)
	public Iterable<Usuario> findUsuariosVip();
	
	@Query(value = "SELECT * FROM USUARIOS WHERE VIP = false", nativeQuery = true)
	public Iterable<Usuario> findUsuariosNoVip();
	
	@Query(value = "SELECT * FROM USUARIOS WHERE VIP = true && MONTH(FECHA_CAMBIO_CATEGORIA) = MONTH(?1)", nativeQuery = true)
	public Iterable<Usuario> findUsuarioVipByMes(String fecha);
	
	@Query(value = "SELECT * FROM USUARIOS WHERE VIP = false && MONTH(FECHA_CAMBIO_CATEGORIA) = MONTH(?1)", nativeQuery = true)
	public Iterable<Usuario> findUsuarioNoVipByMes(String fecha);
}
