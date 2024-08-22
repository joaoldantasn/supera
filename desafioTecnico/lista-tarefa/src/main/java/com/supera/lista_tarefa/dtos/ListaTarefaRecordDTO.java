package com.supera.lista_tarefa.dtos;

import com.supera.lista_tarefa.model.ListaTarefa;

import lombok.Builder;

@Builder
public record ListaTarefaRecordDTO(
		Long id,
		String titulo
		) {
	
	public ListaTarefaRecordDTO(ListaTarefa listaTarefa) {
        this(listaTarefa.getId(), listaTarefa.getTitulo());
    }
	
}
