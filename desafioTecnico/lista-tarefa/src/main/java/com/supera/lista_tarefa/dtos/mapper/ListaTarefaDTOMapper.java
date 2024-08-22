package com.supera.lista_tarefa.dtos.mapper;

import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.supera.lista_tarefa.dtos.ListaComTarefaDTO;
import com.supera.lista_tarefa.dtos.TarefaDTO;
import com.supera.lista_tarefa.model.ListaTarefa;
@Service
public class ListaTarefaDTOMapper implements Function<ListaTarefa, ListaComTarefaDTO>{

	@Override
	public ListaComTarefaDTO apply(ListaTarefa lista) {
		return ListaComTarefaDTO.builder()
                .id(lista.getId())
                .titulo(lista.getTitulo())
                .tarefas(lista.getTarefas().stream()
                        .map(TarefaDTO::new)
                        .collect(Collectors.toSet()))
                .build();
	}

}
