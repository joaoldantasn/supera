package com.supera.lista_tarefa.dtos.listatarefa;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import com.supera.lista_tarefa.dtos.tarefa.TarefaDTO;

public class ListaComTarefaDTOTest {

    @Test
    public void testListaComTarefaDTOBuilder() {
        TarefaDTO tarefa1 = TarefaDTO.builder()
                .id(1L)
                .titulo("Tarefa 1")
                .descricao("Descrição 1")
                .build();

        TarefaDTO tarefa2 = TarefaDTO.builder()
                .id(2L)
                .titulo("Tarefa 2")
                .descricao("Descrição 2")
                .build();

        Set<TarefaDTO> tarefas = new HashSet<>();
        tarefas.add(tarefa1);
        tarefas.add(tarefa2);

        ListaComTarefaDTO listaComTarefaDTO = ListaComTarefaDTO.builder()
                .id(1L)
                .titulo("Lista de Tarefas")
                .tarefas(tarefas)
                .build();

        assertEquals(1L, listaComTarefaDTO.id());
        assertEquals("Lista de Tarefas", listaComTarefaDTO.titulo());
        assertEquals(2, listaComTarefaDTO.tarefas().size());
    }
}
