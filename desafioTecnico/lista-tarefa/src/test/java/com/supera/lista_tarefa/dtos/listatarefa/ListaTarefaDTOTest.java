package com.supera.lista_tarefa.dtos.listatarefa;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.supera.lista_tarefa.model.ListaTarefa;

public class ListaTarefaDTOTest {

    @Test
    public void testListaTarefaDTOConstructorWithEntity() {
        ListaTarefa listaTarefa = new ListaTarefa(1L, "Lista de Tarefas");
        ListaTarefaDTO listaTarefaDTO = new ListaTarefaDTO(listaTarefa);

        assertEquals(1L, listaTarefaDTO.id());
        assertEquals("Lista de Tarefas", listaTarefaDTO.titulo());
    }

    @Test
    public void testListaTarefaDTOBuilder() {
        ListaTarefaDTO listaTarefaDTO = ListaTarefaDTO.builder()
                .id(1L)
                .titulo("Lista de Tarefas")
                .build();

        assertEquals(1L, listaTarefaDTO.id());
        assertEquals("Lista de Tarefas", listaTarefaDTO.titulo());
    }
}