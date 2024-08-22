package com.supera.lista_tarefa.dtos.listatarefa;

import com.supera.lista_tarefa.model.ListaTarefa;

import lombok.Builder;

@Builder
public record ListaTarefaDTO(
		Long id,
		String titulo
		) {
	
	public ListaTarefaDTO(ListaTarefa listaTarefa) {
        this(listaTarefa.getId(), listaTarefa.getTitulo());
    }
	
}
