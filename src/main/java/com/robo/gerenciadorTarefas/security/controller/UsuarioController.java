package com.robo.gerenciadorTarefas.security.controller;

import java.net.URI;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.robo.gerenciadorTarefas.Response.Response;
import com.robo.gerenciadorTarefas.Services.UsuarioService;
import com.robo.gerenciadorTarefas.entities.Usuario;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "*")
public class UsuarioController {
	
	@Autowired
	UsuarioService usuarioService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@PostMapping
	//@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Response<Usuario>> create(HttpServletRequest request,
												@RequestBody Usuario usuario,
												BindingResult result){
		Response<Usuario> response = new Response<Usuario>();
		try {
			validateCreateUser(usuario, result);
			if(result.hasErrors()) {
				result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
				return ResponseEntity.badRequest().body(response);
			}
			usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
			Usuario userPersisted = (Usuario) usuarioService.createOrUpdate(usuario);
			response.setData(userPersisted);
		} catch (DuplicateKeyException dE) {
			response.getErrors().add("E-mail já existe");
			return ResponseEntity.badRequest().body(response);
		} catch (Exception e) {
			response.getErrors().add(e.getMessage());
			return ResponseEntity.badRequest().body(response);
		} 
		
		
		return ResponseEntity.ok(response);
	}
	
	private void validateCreateUser(Usuario usuario, BindingResult result) {
		if(usuario.getEmail() == null) {
			result.addError(new ObjectError("Usuario", "Email não informado"));
		}
	}
	
	@PutMapping
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Response<Usuario>> update(HttpServletRequest request,
												@RequestBody Usuario usuario,
												BindingResult result){
		Response<Usuario> response = new Response<Usuario>();
		try {
			validateUpdateUser(usuario,result);
			if(result.hasErrors()) {
				result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
				return ResponseEntity.badRequest().body(response);
			}
			usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
			Usuario userPersisted = (Usuario) usuarioService.createOrUpdate(usuario);
			response.setData(userPersisted);
		} catch (Exception e) {
			response.getErrors().add(e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}
		return ResponseEntity.ok(response);
	}
	
	private void validateUpdateUser(Usuario usuario, BindingResult result) {
		if(usuario.getId() == null) {
			result.addError(new ObjectError("Usuario", "Id não informado"));
		}
		if(usuario.getEmail() == null) {
			result.addError(new ObjectError("Usuario", "Email não informado"));
		}
	}
	
	@GetMapping("/{id}")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Response<Usuario>> findById(@PathVariable("id") String id){
		Response<Usuario> response = new Response<Usuario>();
		Usuario usuario = usuarioService.findById(id);
		if(usuario == null) {
			response.getErrors().add("Usuário não cadastrado");
			return ResponseEntity.badRequest().body(response);
		}
		response.setData(usuario);
		return ResponseEntity.ok(response);
		
	}
	
	@DeleteMapping("/{id}")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Response<String>> delete(@PathVariable("id") String id){
		Response<String> response = new Response<String>();
		Usuario usuario = usuarioService.findById(id);
		if(usuario == null) {
			response.getErrors().add("Usuário não cadastrado");
			return ResponseEntity.badRequest().body(response);
		}
		usuarioService.delete(id);
		return ResponseEntity.ok(new Response<String>());
	
	}
	
	@GetMapping("/usuarios")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Response<List<Usuario>>> findAll(){
		Response<List<Usuario>> response = new Response<List<Usuario>>();
		List<Usuario> usuarios = usuarioService.findAll();
		response.setData(usuarios);
		return ResponseEntity.ok(response);
	}
	
//	@PostMapping("/usuario")
//	public ResponseEntity<Usuario> InserirUsuario(@RequestBody Usuario usuario){
//		usuario = usuarioService.InserirUsuario(usuario);
//		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
//				.buildAndExpand(usuario.getId()).toUri();
//		return ResponseEntity.created(uri).build();
//	}
//
}
