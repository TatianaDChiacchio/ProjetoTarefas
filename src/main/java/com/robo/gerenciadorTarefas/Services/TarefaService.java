package com.robo.gerenciadorTarefas.Services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.robo.gerenciadorTarefas.Repositories.AlteracaoStatusRepository;
import com.robo.gerenciadorTarefas.Repositories.TarefaRepository;
import com.robo.gerenciadorTarefas.entities.AlteracaoStatus;
import com.robo.gerenciadorTarefas.entities.Tarefa;


@Service
public class TarefaService {
	
	@Autowired
	private TarefaRepository tarefaRepository;
	
	public List<Tarefa> findAll() {
		return tarefaRepository.findAll();
	}
					
	public List<Tarefa> findByIdUsuario(String id_usuario) {
		List<Tarefa> tarefas = tarefaRepository.findByIdUsuario(id_usuario);
		return tarefas;
			
	}
	
	public Tarefa findById(String id) {
		Optional<Tarefa> tarefa = tarefaRepository.findById(id);
		return tarefa.orElseThrow();
	}
	
	
	public Tarefa buscaPeloTitulo(String titulo) {
		Tarefa tarefa = tarefaRepository.findByTituloIgnoreCaseContainingOrderByDataDesc(titulo);
		return tarefa;
	}
	
	public Tarefa createOrUpdate(Tarefa tarefa) {
		return tarefaRepository.save(tarefa);
	}
	
	public void delete(String id) {
		tarefaRepository.deleteById(id);
	}
	
	
	//busca pelo título e data
	
	public List<Tarefa> findByTituloAndData(String titulo, Date data) {
		List<Tarefa> tarefas = tarefaRepository.findByTituloIgnoreCaseContainingAndDataOrderByDataDesc(titulo,data);
		return tarefas;
			
	}
	
	//busca pelo status e data
	
	public List<Tarefa> findByStatusAndData(String status, Date data) {
		List<Tarefa> tarefas = tarefaRepository.findByStatusAndData(status, data);
		return tarefas;
				
	}
	
	//busca pelo título e status
	
	public List<Tarefa> findByStatusAndtitulo(String titulo, String status) {
		List<Tarefa> tarefas = tarefaRepository.findByTituloAndStatus(titulo, status);
		return tarefas;
				
	}
	
	//busca pelo status
	
		public List<Tarefa> findByStatus(String status) {
			List<Tarefa> tarefas = tarefaRepository.findByStatus(status);
			return tarefas;
					
		}
}
