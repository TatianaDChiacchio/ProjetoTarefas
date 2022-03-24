package com.robo.gerenciadorTarefas.Repositories;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.robo.gerenciadorTarefas.entities.Tarefa;


public interface TarefaRepository extends JpaRepository<Tarefa,String>{
	
	@Query(value = "SELECT * FROM db_tarefas.tarefa where id_usuario = :id_usuario", nativeQuery = true)
	List<Tarefa> findByIdUsuario(String id_usuario);
	
	Tarefa findByTituloIgnoreCaseContainingOrderByDataDesc(String titulo);
	
	List<Tarefa> findByTituloIgnoreCaseContainingAndDataOrderByDataDesc(String titulo, Date data);
	
	@Query(value = "SELECT * FROM db_tarefas.tarefa where titulo = :titulo AND status =:status", nativeQuery = true)
	List<Tarefa> findByTituloAndStatus(String titulo, String status);
	
	@Query(value = "SELECT * FROM db_tarefas.tarefa where status =:status", nativeQuery = true)
	List<Tarefa> findByStatus(String status);
	
	@Query(value = "SELECT * FROM db_tarefas.tarefa where status =:status AND data =:data", nativeQuery = true)
	List<Tarefa> findByStatusAndData(String status, Date data);
	
	

}
