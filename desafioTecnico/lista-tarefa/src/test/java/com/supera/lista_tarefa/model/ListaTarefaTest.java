package com.supera.lista_tarefa.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class ListaTarefaTest {

    @Test
    public void testListaTarefaConstructorAndGetters() {
        ListaTarefa listaTarefa = new ListaTarefa(1L, "Minha Lista");

        assertEquals(1L, listaTarefa.getId());
        assertEquals("Minha Lista", listaTarefa.getTitulo());
        assertTrue(listaTarefa.getTarefas().isEmpty());
    }

    @Test
    public void testListaTarefaSetters() {
        ListaTarefa listaTarefa = new ListaTarefa();
        listaTarefa.setId(2L);
        listaTarefa.setTitulo("Nova Lista");

        assertEquals(2L, listaTarefa.getId());
        assertEquals("Nova Lista", listaTarefa.getTitulo());
    }

    @Test
    public void testAdicionarTarefaNaLista() {
        ListaTarefa listaTarefa = new ListaTarefa(1L, "Minha Lista");
        Tarefa tarefa = new Tarefa(1L, "Tarefa 1", "Descrição 1", null, null, false, false);

        listaTarefa.getTarefas().add(tarefa);

        assertFalse(listaTarefa.getTarefas().isEmpty());
        assertEquals(1, listaTarefa.getTarefas().size());
        assertEquals(tarefa, listaTarefa.getTarefas().iterator().next());
    }
}
