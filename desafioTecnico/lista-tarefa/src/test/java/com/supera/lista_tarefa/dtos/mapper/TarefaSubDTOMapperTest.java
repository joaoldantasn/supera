package com.supera.lista_tarefa.dtos.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import com.supera.lista_tarefa.dtos.tarefa.TarefaComSubDTO;
import com.supera.lista_tarefa.model.SubTarefa;
import com.supera.lista_tarefa.model.Tarefa;

public class TarefaSubDTOMapperTest {

    @Test
    public void testApply() {
        SubTarefa subTarefa1 = new SubTarefa(1L, "SubTarefa 1");
        SubTarefa subTarefa2 = new SubTarefa(2L, "SubTarefa 2");

        Set<SubTarefa> subtarefas = new HashSet<>();
        subtarefas.add(subTarefa1);
        subtarefas.add(subTarefa2);

        Tarefa tarefa = new Tarefa(1L, "Tarefa 1", "Descrição 1", LocalDate.now(), LocalDate.now().plusDays(1), false, false);
        tarefa.setSubtarefas(subtarefas);

        TarefaSubDTOMapper mapper = new TarefaSubDTOMapper();
        TarefaComSubDTO dto = mapper.apply(tarefa);

        assertEquals(1L, dto.id());
        assertEquals("Tarefa 1", dto.titulo());
        assertEquals("Descrição 1", dto.descricao());
        assertEquals(2, dto.subtarefas().size());

        // Verificando se as subtarefas foram mapeadas corretamente
        assertTrue(dto.subtarefas().stream().anyMatch(st -> st.id().equals(1L) && st.nome().equals("SubTarefa 1")));
        assertTrue(dto.subtarefas().stream().anyMatch(st -> st.id().equals(2L) && st.nome().equals("SubTarefa 2")));
    }
}
