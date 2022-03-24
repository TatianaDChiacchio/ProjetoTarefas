package com.robo.gerenciadorTarefas.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.robo.gerenciadorTarefas.Repositories.UsuarioRepository;
import com.robo.gerenciadorTarefas.entities.Usuario;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	PasswordEncoder encoder;
	
	
	public List<Usuario> findAll(){
		return (List<Usuario>) usuarioRepository.findAll();
	
	}
	
	public Usuario findByEmail(String email) {
		return usuarioRepository.findByEmail(email);
	}
	
	public Usuario createOrUpdate(Usuario usuario) {
		return usuarioRepository.save(usuario);
	}
	
	
	public Usuario findById(String id) {
		Optional<Usuario> usuario = usuarioRepository.findById(id);
		return usuario.orElseThrow();
	}
	

	public void delete(String id) {
		usuarioRepository.deleteById(id);
	}
	
	public Usuario InserirUsuario(Usuario usuario) {
		usuario.setPassword(encoder.encode(usuario.getPassword()));
		return usuarioRepository.save(usuario);
	}
}
