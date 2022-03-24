package com.robo.gerenciadorTarefas.security.model;

import com.robo.gerenciadorTarefas.entities.Usuario;

public class CurrentUser {

	private String token;
	private Usuario usuario;
	
	
	public CurrentUser(String token, Usuario usuario) {
		this.token = token;
		this.usuario = usuario;
	}
	
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	
}
