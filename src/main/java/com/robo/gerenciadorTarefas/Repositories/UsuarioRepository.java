package com.robo.gerenciadorTarefas.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.robo.gerenciadorTarefas.entities.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario,String>{
	
	Usuario findByEmail(String email);

}
