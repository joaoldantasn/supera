package com.supera.lista_tarefa.dtos.listatarefa;

import java.util.Set;

import com.supera.lista_tarefa.dtos.tarefa.TarefaDTO;

import lombok.Builder;

@Builder
public record ListaComTarefaDTO(
		Long id,
		String titulo,
		Set<TarefaDTO> tarefas
		) {

}
