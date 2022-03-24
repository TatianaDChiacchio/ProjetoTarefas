package com.robo.gerenciadorTarefas.Repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.robo.gerenciadorTarefas.entities.AlteracaoStatus;
import com.robo.gerenciadorTarefas.entities.Tarefa;

public interface AlteracaoStatusRepository extends JpaRepository<AlteracaoStatus,String>{
	
	@Query(value = "SELECT * FROM db_tarefas.alteracaoStatus where id_tarefa = :id_tarefa", nativeQuery = true)
	List<AlteracaoStatus> findByIdTarefaoOrderByDataAlteracaoDesc(String  id_tarefa);
	
	
}
