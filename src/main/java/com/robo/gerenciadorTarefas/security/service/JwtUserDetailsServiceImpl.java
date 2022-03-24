package com.robo.gerenciadorTarefas.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.robo.gerenciadorTarefas.Services.UsuarioService;
import com.robo.gerenciadorTarefas.entities.Usuario;
import com.robo.gerenciadorTarefas.security.jwt.JwtUserFactory;

@Service
public class JwtUserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	private UsuarioService usuarioSerrvice;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		Usuario usuario = usuarioSerrvice.findByEmail(email);
		if(usuario == null) {
			throw new UsernameNotFoundException(String.format("Usuario não cadastrado '%s'.", email));
		}else {
			return JwtUserFactory.create(usuario);
		}
	}
	
	
	
	
	

}
