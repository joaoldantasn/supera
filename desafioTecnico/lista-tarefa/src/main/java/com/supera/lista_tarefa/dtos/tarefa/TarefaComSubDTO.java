package com.supera.lista_tarefa.dtos.tarefa;

import java.time.LocalDate;
import java.util.Set;

import com.supera.lista_tarefa.dtos.subtarefas.SubTarefaDTO;

import lombok.Builder;

@Builder
public record TarefaComSubDTO(
		Long id,
		String titulo,
		String descricao,
		LocalDate dataConclusao,
		LocalDate dataPrevistaConclucao,
		boolean concluida,
		boolean favorita,
		Set<SubTarefaDTO> subtarefas
		) {

	
}
