package com.supera.lista_tarefa.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class TarefaTest {

    @Test
    public void testTarefaConstructorAndGetters() {
        LocalDate dataConclusao = LocalDate.of(2024, 8, 22);
        LocalDate dataPrevista = LocalDate.of(2024, 9, 22);

        Tarefa tarefa = new Tarefa(1L, "Título da Tarefa", "Descrição da Tarefa", dataConclusao, dataPrevista, true, false);

        assertEquals(1L, tarefa.getId());
        assertEquals("Título da Tarefa", tarefa.getTitulo());
        assertEquals("Descrição da Tarefa", tarefa.getDescricao());
        assertEquals(dataConclusao, tarefa.getDataConclusao());
        assertEquals(dataPrevista, tarefa.getDataPrevistaConclucao());
        assertTrue(tarefa.isConcluida());
        assertFalse(tarefa.isFavorita());
    }

    @Test
    public void testTarefaSetters() {
        Tarefa tarefa = new Tarefa();

        tarefa.setId(2L);
        tarefa.setTitulo("Novo Título");
        tarefa.setDescricao("Nova Descrição");
        tarefa.setDataConclusao(LocalDate.now());
        tarefa.setDataPrevistaConclucao(LocalDate.now().plusDays(5));
        tarefa.setConcluida(true);
        tarefa.setFavorita(true);

        assertEquals(2L, tarefa.getId());
        assertEquals("Novo Título", tarefa.getTitulo());
        assertEquals("Nova Descrição", tarefa.getDescricao());
        assertNotNull(tarefa.getDataConclusao());
        assertNotNull(tarefa.getDataPrevistaConclucao());
        assertTrue(tarefa.isConcluida());
        assertTrue(tarefa.isFavorita());
    }
}
