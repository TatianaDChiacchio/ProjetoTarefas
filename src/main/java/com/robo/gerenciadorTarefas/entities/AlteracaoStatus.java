package com.robo.gerenciadorTarefas.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.robo.gerenciadorTarefas.enums.StatusEnum;

@Entity
public class AlteracaoStatus {
	
	@Id
	private String id;
	
	private Date DataAlteracao;
	
	private StatusEnum status;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "id_tarefa")
	private Tarefa tarefa;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	

	public Date getDataAlteracao() {
		return DataAlteracao;
	}

	public void setDataAlteracao(Date dataAlteracao) {
		DataAlteracao = dataAlteracao;
	}

	public StatusEnum getStatus() {
		return status;
	}

	public void setStatus(StatusEnum status) {
		this.status = status;
	}

	public Tarefa getTarefa() {
		return tarefa;
	}

	public void setTarefa(Tarefa tarefa) {
		this.tarefa = tarefa;
	}
	
	
	

}
