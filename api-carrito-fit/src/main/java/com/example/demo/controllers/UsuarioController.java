package com.example.demo.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import com.example.demo.models.entity.Usuario;
import com.example.demo.services.UsuarioService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {
	
	  @Autowired
	    protected UsuarioService usuarioService;
	  
	  
	    @GetMapping
	    public ResponseEntity<?> listar() throws ParseException {
	    	
	        return ResponseEntity.ok(usuarioService.findAll());
	    }

	    @GetMapping("/{id}")
	    public ResponseEntity<?> obtenerById(@PathVariable Long id) {
	        Optional o = usuarioService.findById(id);
	        
	        if(o.isEmpty()){
	            return ResponseEntity.notFound().build();
	        }
	        Usuario usuario = (Usuario) o.get();
	    	Date fechaActual = new Date();
	    	
	    	double diasDesdeUltimaCompra = usuarioService.diferenciaDiasEntreFechas(fechaActual, usuario.getUltimaCompra());
	    	
	        if( diasDesdeUltimaCompra > 30) {
	        	usuario.setVip(false);
	        	usuario.setFechaCambioCategoria(new Date());
	        	usuarioService.save(usuario);
	        }
	        
	        return ResponseEntity.ok().body(usuario);
	    }

	    @PostMapping
	    public ResponseEntity<?> crear(@Valid @RequestBody Usuario usuario, BindingResult result) {
	    	
	    	if(result.hasErrors()) {
	    		return this.validar(result);
	    	}
	    	
	        Usuario nuevoUsuario = usuarioService.save(usuario);

	        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoUsuario);
	    }
	    
	    @PutMapping("/{id}")
	    public ResponseEntity<?> editar(@Valid @RequestBody Usuario usuario, BindingResult result,
	    		@PathVariable Long id){
	    	
	    	if(result.hasErrors()) {
	    		return this.validar(result);
	    	}
	    	
	    	Optional o = usuarioService.findById(id);

			if (o.isEmpty()) {
				return ResponseEntity.notFound().build();
			}
			Usuario usuarioActual = (Usuario) o.get();
			usuarioActual.setNombre(usuario.getNombre());
			usuarioActual.setApellido(usuario.getApellido());
			usuarioActual.setEmail(usuario.getEmail());

			return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.save(usuarioActual));
	
	    }


	    @DeleteMapping("/{id}")
	    public ResponseEntity<?> eliminar(@PathVariable Long id){
	        usuarioService.deleteById(id);
	        return ResponseEntity.noContent().build();

	    }
	    
	    protected  ResponseEntity<?> validar(BindingResult result) {
			HashMap<String, Object> errores = new HashMap<>();
			
			result.getFieldErrors().forEach(err ->{
				errores.put(err.getField(), "El campo " + err.getField() + " " + err.getDefaultMessage());
			});
			
			return ResponseEntity.badRequest().body(errores);
		}
	    
	    @GetMapping("/usuarios-vip-mes/{mes}")
	    public ResponseEntity<?> obtenerUsuariosVipMes(@PathVariable int mes) {
	    	String fecha = "2021-"+mes+"-01";
	    	
	    	return ResponseEntity.ok().body(usuarioService.findUsuarioVipByMes(fecha));
	    }
	    
	    @GetMapping("/usuarios-novip-mes/{mes}")
	    public ResponseEntity<?> obtenerUsuariosNoVipMes(@PathVariable int mes) {
	    	String fecha = "2021-"+mes+"-01";
	    	
	    	return ResponseEntity.ok().body(usuarioService.findUsuarioNoVipByMes(fecha));
	    }
	    
	    @GetMapping("usuarios-vip")
	    public ResponseEntity<?> obtenerUsuariosVip(){
	    	return ResponseEntity.ok().body(usuarioService.findUsuariosVip());
	    }
	    
	    @GetMapping("usuarios-novip")
	    public ResponseEntity<?> obtenerUsuariosNoVip(){
	    	return ResponseEntity.ok().body(usuarioService.findUsuariosNoVip());
	    }
}
