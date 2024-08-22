package com.supera.lista_tarefa.controllers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.supera.lista_tarefa.dtos.tarefa.TarefaComSubDTO;
import com.supera.lista_tarefa.dtos.tarefa.TarefaDTO;
import com.supera.lista_tarefa.model.Tarefa;
import com.supera.lista_tarefa.services.TarefaService;

@WebMvcTest(TarefaController.class)
public class TarefaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TarefaService tarefaService;

    @MockBean
    private PagedResourcesAssembler<TarefaDTO> pagedResourcesAssembler;

    @Test
    public void testGetTarefaById() throws Exception {
        TarefaComSubDTO tarefaDTO = new TarefaComSubDTO(1L, "Tarefa 1", "Descrição 1", null, null, false, false, Set.of());

        when(tarefaService.findById(1L)).thenReturn(tarefaDTO);

        mockMvc.perform(MockMvcRequestBuilders.get("/tarefas/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.titulo").value("Tarefa 1"));

        verify(tarefaService, times(1)).findById(1L);
    }
    @Test
    public void testCreateTarefa() throws Exception {
        TarefaDTO tarefaDTO = new TarefaDTO(null, "Nova Tarefa", "Descrição", null, null, false, false);
        TarefaComSubDTO createdTarefa = new TarefaComSubDTO(1L, "Nova Tarefa", "Descrição", null, null, false, false, Set.of());

        when(tarefaService.createTarefa(any(TarefaDTO.class))).thenReturn(createdTarefa);

        mockMvc.perform(MockMvcRequestBuilders.post("/tarefas/criar")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"titulo\": \"Nova Tarefa\", \"descricao\": \"Descrição\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.titulo").value("Nova Tarefa"));

        verify(tarefaService, times(1)).createTarefa(any(TarefaDTO.class));
    }
    @Test
    public void testUpdateTarefa() throws Exception {
        TarefaComSubDTO tarefaDTO = new TarefaComSubDTO(null, "Tarefa Atualizada", "Descrição Atualizada", null, null, false, false, Set.of());
        Tarefa updatedTarefa = new Tarefa(1L, "Tarefa Atualizada", "Descrição Atualizada", null, null, false, false);

        when(tarefaService.updateTarefa(eq(1L), any(TarefaComSubDTO.class))).thenReturn(updatedTarefa);

        mockMvc.perform(MockMvcRequestBuilders.put("/tarefas/atualizar/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"titulo\": \"Tarefa Atualizada\", \"descricao\": \"Descrição Atualizada\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.titulo").value("Tarefa Atualizada"));

        verify(tarefaService, times(1)).updateTarefa(eq(1L), any(TarefaComSubDTO.class));
    }
    @Test
    public void testDeleteTarefa() throws Exception {
        doNothing().when(tarefaService).deleteTarefa(1L);

        mockMvc.perform(MockMvcRequestBuilders.delete("/tarefas/deletar/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        verify(tarefaService, times(1)).deleteTarefa(1L);
    }
}
