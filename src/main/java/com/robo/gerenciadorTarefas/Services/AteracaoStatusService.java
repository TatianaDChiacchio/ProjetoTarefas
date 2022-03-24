package com.robo.gerenciadorTarefas.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.robo.gerenciadorTarefas.Repositories.AlteracaoStatusRepository;
import com.robo.gerenciadorTarefas.entities.AlteracaoStatus;
import com.robo.gerenciadorTarefas.entities.Tarefa;

@Service
public class AteracaoStatusService {
	
	@Autowired
	AlteracaoStatusRepository alteracaoStatusRepository;
	
	public List<AlteracaoStatus> findAll() {
		return alteracaoStatusRepository.findAll();
	}
	
	public List<AlteracaoStatus> findByIdUsuario(String id_tarefa) {
		List<AlteracaoStatus> alteracaoStatus = alteracaoStatusRepository.findByIdTarefaoOrderByDataAlteracaoDesc(id_tarefa);
		return alteracaoStatus;
			
	}
	
	public AlteracaoStatus findById(String id) {
		Optional<AlteracaoStatus> alteracaoStatus = alteracaoStatusRepository.findById(id);
		return alteracaoStatus.orElseThrow();
	}
	
	public void delete(String id) {
		 alteracaoStatusRepository.deleteById(id);
	}
	
	public AlteracaoStatus createAlteracaoStatus(AlteracaoStatus alteracaoStatus) {
		return alteracaoStatusRepository.save(alteracaoStatus);
	}

}
