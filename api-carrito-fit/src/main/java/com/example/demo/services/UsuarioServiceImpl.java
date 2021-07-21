package com.example.demo.services;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.entity.Usuario;
import com.example.demo.models.repository.UsuarioRepository;

@Service
public class UsuarioServiceImpl implements UsuarioService{

	@Autowired
	private UsuarioRepository repository;
	
	@Override
	public Iterable<Usuario> findAll() {
		return repository.findAll();
	}

	@Override
	public Optional<Usuario> findById(Long id) {
		return repository.findById(id);
	}

	@Override
	public Usuario save(Usuario usuario) {
		return repository.save(usuario);
	}

	@Override
	public void deleteById(Long id) {
		repository.deleteById(id);
		
	}

	@Override
	public double diferenciaDiasEntreFechas(Date fecha1, Date fecha2) {
		double difFechas= (double)(fecha1.getTime() - fecha2.getTime());
    	double dias = (difFechas/ 86400000);
    	
    	return dias;
	}

	@Override
	public Usuario findUsuarioByUsername(String username) {
		return repository.findUsuarioByUsername(username);
	}

	@Override
	public Iterable<Usuario> findUsuarioVipByMes(String fecha) {
		return repository.findUsuarioVipByMes(fecha);
	}

	@Override
	public Iterable<Usuario> findUsuarioNoVipByMes(String fecha) {
		return repository.findUsuarioNoVipByMes(fecha);
	}

	@Override
	public Iterable<Usuario> findUsuariosVip() {
		return repository.findUsuariosVip();
	}

	@Override
	public Iterable<Usuario> findUsuariosNoVip() {
		return repository.findUsuariosNoVip();
	}

}
