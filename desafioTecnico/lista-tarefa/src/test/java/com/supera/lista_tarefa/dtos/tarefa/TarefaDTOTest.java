package com.supera.lista_tarefa.dtos.tarefa;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import com.supera.lista_tarefa.model.Tarefa;

public class TarefaDTOTest {

    @Test
    public void testTarefaDTOConstructorWithEntity() {
        LocalDate dataConclusao = LocalDate.of(2024, 8, 22);
        LocalDate dataPrevista = LocalDate.of(2024, 9, 22);

        Tarefa tarefa = new Tarefa(1L, "Título", "Descrição", dataConclusao, dataPrevista, true, false);
        TarefaDTO tarefaDTO = new TarefaDTO(tarefa);

        assertEquals(1L, tarefaDTO.id());
        assertEquals("Título", tarefaDTO.titulo());
        assertEquals("Descrição", tarefaDTO.descricao());
        assertEquals(dataConclusao, tarefaDTO.dataConclusao());
        assertEquals(dataPrevista, tarefaDTO.dataPrevistaConclucao());
        assertTrue(tarefaDTO.concluida());
        assertFalse(tarefaDTO.favorita());
    }

    @Test
    public void testTarefaDTOBuilder() {
        LocalDate dataConclusao = LocalDate.of(2024, 8, 22);
        LocalDate dataPrevista = LocalDate.of(2024, 9, 22);

        TarefaDTO tarefaDTO = TarefaDTO.builder()
                .id(1L)
                .titulo("Título")
                .descricao("Descrição")
                .dataConclusao(dataConclusao)
                .dataPrevistaConclucao(dataPrevista)
                .concluida(true)
                .favorita(false)
                .build();

        assertEquals(1L, tarefaDTO.id());
        assertEquals("Título", tarefaDTO.titulo());
        assertEquals("Descrição", tarefaDTO.descricao());
        assertEquals(dataConclusao, tarefaDTO.dataConclusao());
        assertEquals(dataPrevista, tarefaDTO.dataPrevistaConclucao());
        assertTrue(tarefaDTO.concluida());
        assertFalse(tarefaDTO.favorita());
    }
}
