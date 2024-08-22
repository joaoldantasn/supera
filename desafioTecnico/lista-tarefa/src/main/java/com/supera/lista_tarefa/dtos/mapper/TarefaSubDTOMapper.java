package com.supera.lista_tarefa.dtos.mapper;

import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.supera.lista_tarefa.dtos.subtarefas.SubTarefaDTO;
import com.supera.lista_tarefa.dtos.tarefa.TarefaComSubDTO;
import com.supera.lista_tarefa.model.Tarefa;

@Service
public class TarefaSubDTOMapper implements Function<Tarefa, TarefaComSubDTO>{

	@Override
	public TarefaComSubDTO apply(Tarefa tarefa) {
		return TarefaComSubDTO.builder()
                .id(tarefa.getId())
                .titulo(tarefa.getTitulo())
                .descricao(tarefa.getDescricao())
                .dataConclusao(tarefa.getDataConclusao())
                .dataPrevistaConclucao(tarefa.getDataPrevistaConclucao())
                .concluida(tarefa.isConcluida())
                .favorita(tarefa.isFavorita())
                .subtarefas(tarefa.getSubtarefas().stream()
                        .map(SubTarefaDTO::new)
                        .collect(Collectors.toSet()))
                .build();
	}

}
