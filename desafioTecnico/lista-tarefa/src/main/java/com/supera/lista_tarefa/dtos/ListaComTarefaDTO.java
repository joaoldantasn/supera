package com.supera.lista_tarefa.dtos;

import java.util.Set;

import lombok.Builder;

@Builder
public record ListaComTarefaDTO(
		Long id,
		String titulo,
		Set<TarefaDTO> tarefas
		) {

}
