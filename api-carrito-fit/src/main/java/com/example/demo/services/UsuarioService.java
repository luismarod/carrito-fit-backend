package com.example.demo.services;

import java.util.Date;
import java.util.Optional;

import com.example.demo.models.entity.Usuario;

public interface UsuarioService {

	public Iterable<Usuario> findAll();

	public Optional<Usuario> findById(Long id);

	public Usuario save(Usuario usuario);

	public void deleteById(Long id);
	
	public Usuario findUsuarioByUsername(String username);
	
	public Iterable<Usuario> findUsuariosVip();
	
	public Iterable<Usuario> findUsuariosNoVip();
	
	public Iterable<Usuario> findUsuarioVipByMes(String fecha);
	
	public Iterable<Usuario> findUsuarioNoVipByMes(String fecha);
	
	public double diferenciaDiasEntreFechas(Date fecha1, Date fecha2);
}
