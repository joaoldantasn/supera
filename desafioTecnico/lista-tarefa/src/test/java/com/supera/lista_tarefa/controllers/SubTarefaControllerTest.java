package com.supera.lista_tarefa.controllers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.supera.lista_tarefa.dtos.subtarefas.SubTarefaDTO;
import com.supera.lista_tarefa.services.SubTarefaService;

@WebMvcTest(SubTarefaController.class)
public class SubTarefaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SubTarefaService subTarefaService;

    @Test
    public void testGetById() throws Exception {
        SubTarefaDTO subTarefaDTO = new SubTarefaDTO(1L, "SubTarefa 1");

        when(subTarefaService.findById(1L)).thenReturn(subTarefaDTO);

        mockMvc.perform(MockMvcRequestBuilders.get("/sub-tarefas/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.nome").value("SubTarefa 1"));

        verify(subTarefaService, times(1)).findById(1L);
    }
    
    @Test
    public void testUpdateSubTarefaById() throws Exception {
        SubTarefaDTO updatedSubTarefaDTO = new SubTarefaDTO(1L, "SubTarefa Atualizada");

        when(subTarefaService.updateSubTarefa(any(SubTarefaDTO.class), eq(1L))).thenReturn(updatedSubTarefaDTO);

        mockMvc.perform(MockMvcRequestBuilders.put("/sub-tarefas/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"nome\": \"SubTarefa Atualizada\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.nome").value("SubTarefa Atualizada"));

        verify(subTarefaService, times(1)).updateSubTarefa(any(SubTarefaDTO.class), eq(1L));
    }
    
    @Test
    public void testDeleteById() throws Exception {
        doNothing().when(subTarefaService).deleteSubTarefaById(1L);

        mockMvc.perform(MockMvcRequestBuilders.delete("/sub-tarefas/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        verify(subTarefaService, times(1)).deleteSubTarefaById(1L);
    }
    
}