package com.supera.lista_tarefa.dtos.subtarefa;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.supera.lista_tarefa.dtos.subtarefas.SubTarefaDTO;
import com.supera.lista_tarefa.model.SubTarefa;

public class SubTarefaDTOTest {

    @Test
    public void testSubTarefaDTOConstructorWithEntity() {
        SubTarefa subTarefa = new SubTarefa(1L, "SubTarefa 1");
        SubTarefaDTO subTarefaDTO = new SubTarefaDTO(subTarefa);

        assertEquals(1L, subTarefaDTO.id());
        assertEquals("SubTarefa 1", subTarefaDTO.nome());
    }

    @Test
    public void testSubTarefaDTOBuilder() {
        SubTarefaDTO subTarefaDTO = SubTarefaDTO.builder()
                .id(2L)
                .nome("SubTarefa 2")
                .build();

        assertEquals(2L, subTarefaDTO.id());
        assertEquals("SubTarefa 2", subTarefaDTO.nome());
    }
}