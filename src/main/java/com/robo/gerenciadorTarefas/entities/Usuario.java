package com.robo.gerenciadorTarefas.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.robo.gerenciadorTarefas.enums.ProfileEnum;

@Entity
public class Usuario {
	
	@Id
	private String id;
	
	//@Column(unique = true)
	//@NotNull(message = "O email é obrigatório")
	private String email;
	
	
	//@NotNull(message = "A senha é obrigatória")
	//@Range(min=6,message="A senha deve ter no mínimo 6 caracteres")
	private String password;
	
	
	//@JsonIgnore
	//@OneToMany(mappedBy = "tarefa")
	//private List<Tarefa> tarefa = new ArrayList<>();
	
	private ProfileEnum profile;
	
	

	public Usuario(String id, @NotNull(message = "O email é obrigatório") String email,
			@NotNull(message = "A senha é obrigatória") String password, ProfileEnum profile) {
	
		this.id = id;
		this.email = email;
		this.password = password;
		this.profile = profile;
	}

	public Usuario() {
		
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public ProfileEnum getProfile() {
		return profile;
	}

	public void setProfile(ProfileEnum profile) {
		this.profile = profile;
	}
	
	
	

}
