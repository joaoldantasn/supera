package com.supera.lista_tarefa.dtos.tarefa;

import java.time.LocalDate;

import com.supera.lista_tarefa.model.Tarefa;

import lombok.Builder;

@Builder
public record TarefaDTO(
		Long id,
		String titulo,
		String descricao, LocalDate dataConclusao,LocalDate dataPrevistaConclucao,
		boolean concluida, boolean favorita
		) {

	public TarefaDTO(Tarefa tarefa) {
		this(
				tarefa.getId(), 
				tarefa.getTitulo(), 
				tarefa.getDescricao(), 
				tarefa.getDataConclusao(),
				tarefa.getDataPrevistaConclucao(),
				tarefa.isConcluida(),
				tarefa.isFavorita()
				);
	}
	
}
