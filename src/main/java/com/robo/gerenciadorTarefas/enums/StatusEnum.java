package com.robo.gerenciadorTarefas.enums;

public enum StatusEnum {
	
	Aberto,
	Designado,
	Resolvido,
	Aprovado,
	Reprovado,
	Fechado;
	
	
	public static StatusEnum getStatus(String status) {
		switch(status) {
		case "Aberto": return Aberto;
		case "Designado": return Designado;
		case "Resolvido": return Resolvido;
		case "Aprovado": return Aprovado;
		case "Reprovado": return Reprovado;
		case "Fechado": return Fechado;
		default: return Aberto;
		}
	}

}
