package com.example.demo.controllers;

import java.io.IOException;
import java.util.HashMap;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
import com.example.demo.models.entity.Usuario;
import com.example.demo.services.ProductoService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/productos")
public class ProductoController {

	@Autowired
	protected ProductoService productoService;

	@GetMapping
	public ResponseEntity<?> listar() {
		return ResponseEntity.ok(productoService.findAll());
	}
	
	@GetMapping("/img/{id}")
	public ResponseEntity<?> verFoto(@PathVariable Long id){
		Optional<Producto> optional = productoService.findById(id);
		
		if(optional.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		
		Resource imagen = new ByteArrayResource(optional.get().getFoto());
		
		return ResponseEntity.ok()
				.contentType(MediaType.IMAGE_JPEG)
				.body(imagen);
			
	}

	@PostMapping
	public ResponseEntity<?> crear(@Valid Producto producto, BindingResult result,
			@RequestParam MultipartFile archivo) throws IOException {

		if (result.hasErrors()) {
			return this.validar(result);
		}
		
		if (!archivo.isEmpty()) {
			producto.setFoto(archivo.getBytes());
		}

		productoService.save(producto);

		return ResponseEntity.status(HttpStatus.CREATED).body(producto);
	}

	protected ResponseEntity<?> validar(BindingResult result) {
		HashMap<String, Object> errores = new HashMap<>();

		result.getFieldErrors().forEach(err -> {
			errores.put(err.getField(), "El campo " + err.getField() + " " + err.getDefaultMessage());
		});

		return ResponseEntity.badRequest().body(errores);
	}
}
