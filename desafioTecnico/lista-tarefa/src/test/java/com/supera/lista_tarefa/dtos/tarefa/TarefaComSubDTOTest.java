package com.supera.lista_tarefa.dtos.tarefa;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import com.supera.lista_tarefa.dtos.subtarefas.SubTarefaDTO;

public class TarefaComSubDTOTest {

    @Test
    public void testTarefaComSubDTOBuilder() {
        LocalDate dataConclusao = LocalDate.of(2024, 8, 22);
        LocalDate dataPrevista = LocalDate.of(2024, 9, 22);

        SubTarefaDTO subTarefa1 = SubTarefaDTO.builder()
                .id(1L)
                .nome("SubTarefa 1")
                .build();

        SubTarefaDTO subTarefa2 = SubTarefaDTO.builder()
                .id(2L)
                .nome("SubTarefa 2")
                .build();

        Set<SubTarefaDTO> subtarefas = new HashSet<>();
        subtarefas.add(subTarefa1);
        subtarefas.add(subTarefa2);

        TarefaComSubDTO tarefaComSubDTO = TarefaComSubDTO.builder()
                .id(1L)
                .titulo("Título")
                .descricao("Descrição")
                .dataConclusao(dataConclusao)
                .dataPrevistaConclucao(dataPrevista)
                .concluida(true)
                .favorita(false)
                .subtarefas(subtarefas)
                .build();

        assertEquals(1L, tarefaComSubDTO.id());
        assertEquals("Título", tarefaComSubDTO.titulo());
        assertEquals("Descrição", tarefaComSubDTO.descricao());
        assertEquals(dataConclusao, tarefaComSubDTO.dataConclusao());
        assertEquals(dataPrevista, tarefaComSubDTO.dataPrevistaConclucao());
        assertTrue(tarefaComSubDTO.concluida());
        assertFalse(tarefaComSubDTO.favorita());
        assertEquals(2, tarefaComSubDTO.subtarefas().size());
    }
}