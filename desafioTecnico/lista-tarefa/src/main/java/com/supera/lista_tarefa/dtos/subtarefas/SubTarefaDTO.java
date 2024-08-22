package com.supera.lista_tarefa.dtos.subtarefas;

import com.supera.lista_tarefa.model.SubTarefa;

import lombok.Builder;

@Builder
public record SubTarefaDTO(
		Long id,
		String nome
		) {

	public SubTarefaDTO(SubTarefa subtarefa) {
        this(subtarefa.getId(), subtarefa.getNome());
    }
	
}
