package com.example.demo.models.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;



@Entity
@Table(name = "usuarios")
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String nombre;

	private String apellido;

	private String email;
	
	@Column(columnDefinition = "boolean default false")
	private boolean vip;
	
	@Column(name="ultima_compra")
	private Date ultimaCompra;
	
	@Column(name="fecha_cambio_categoria")
	private Date fechaCambioCategoria;
	
	@Column(unique = true)
	private String username;
	
	private String password;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Role> roles;
	
	@Column(columnDefinition = "boolean default true")
	private boolean enabled;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public boolean isVip() {
		return vip;
	}

	public void setVip(boolean vip) {
		this.vip = vip;
	}

	public Date getUltimaCompra() {
		return ultimaCompra;
	}

	public void setUltimaCompra(Date ultimaCompra) {
		this.ultimaCompra = ultimaCompra;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public Date getFechaCambioCategoria() {
		return fechaCambioCategoria;
	}

	public void setFechaCambioCategoria(Date fechaCambioCategoria) {
		this.fechaCambioCategoria = fechaCambioCategoria;
	}
	
	
	
	
	
	
	

}
