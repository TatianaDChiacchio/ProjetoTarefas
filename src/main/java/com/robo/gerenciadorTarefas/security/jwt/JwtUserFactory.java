package com.robo.gerenciadorTarefas.security.jwt;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.robo.gerenciadorTarefas.entities.Usuario;
import com.robo.gerenciadorTarefas.enums.ProfileEnum;

public class JwtUserFactory {

	public JwtUserFactory() {
	}
	
	public static JwtUser create (Usuario usuario) {
		return new JwtUser(usuario.getId(), usuario.getEmail(), usuario.getPassword(),
				mapToGrantedAuthorities(usuario.getProfile()));
	}
	
	private static List<GrantedAuthority> mapToGrantedAuthorities(ProfileEnum profileEnum){
		List<GrantedAuthority> authoritities = new ArrayList<GrantedAuthority>();
		authoritities.add(new SimpleGrantedAuthority(profileEnum.toString()));
		return authoritities;
	}
	
	

}
