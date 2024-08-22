package com.supera.lista_tarefa.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class SubTarefaTest {

    @Test
    public void testSubTarefaConstructorAndGetters() {
        SubTarefa subTarefa = new SubTarefa(1L, "SubTarefa 1");

        assertEquals(1L, subTarefa.getId());
        assertEquals("SubTarefa 1", subTarefa.getNome());
        assertNull(subTarefa.getTarefa());
    }

    @Test
    public void testSubTarefaSetters() {
        SubTarefa subTarefa = new SubTarefa();
        subTarefa.setId(2L);
        subTarefa.setNome("Nova SubTarefa");

        assertEquals(2L, subTarefa.getId());
        assertEquals("Nova SubTarefa", subTarefa.getNome());
    }

    @Test
    public void testAssociarTarefaASubTarefa() {
        Tarefa tarefa = new Tarefa(1L, "Tarefa 1", "Descrição 1", null, null, false, false);
        SubTarefa subTarefa = new SubTarefa(1L, "SubTarefa 1");

        subTarefa.setTarefa(tarefa);

        assertEquals(tarefa, subTarefa.getTarefa());
    }
}
