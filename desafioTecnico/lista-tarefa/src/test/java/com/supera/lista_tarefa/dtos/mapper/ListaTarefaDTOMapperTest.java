package com.supera.lista_tarefa.dtos.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import com.supera.lista_tarefa.dtos.listatarefa.ListaComTarefaDTO;
import com.supera.lista_tarefa.model.ListaTarefa;
import com.supera.lista_tarefa.model.Tarefa;

public class ListaTarefaDTOMapperTest {

    @Test
    public void testApply() {
        Tarefa tarefa1 = new Tarefa(1L, "Tarefa 1", "Descrição 1", null, null, false, false);
        Tarefa tarefa2 = new Tarefa(2L, "Tarefa 2", "Descrição 2", null, null, false, false);

        Set<Tarefa> tarefas = new HashSet<>();
        tarefas.add(tarefa1);
        tarefas.add(tarefa2);

        ListaTarefa listaTarefa = new ListaTarefa(1L, "Lista de Tarefas");
        listaTarefa.setTarefas(tarefas);

        ListaTarefaDTOMapper mapper = new ListaTarefaDTOMapper();
        ListaComTarefaDTO dto = mapper.apply(listaTarefa);

        assertEquals(1L, dto.id());
        assertEquals("Lista de Tarefas", dto.titulo());
        assertEquals(2, dto.tarefas().size());

        // Verificando se as tarefas foram mapeadas corretamente
        assertTrue(dto.tarefas().stream().anyMatch(t -> t.id().equals(1L) && t.titulo().equals("Tarefa 1")));
        assertTrue(dto.tarefas().stream().anyMatch(t -> t.id().equals(2L) && t.titulo().equals("Tarefa 2")));
    }
}