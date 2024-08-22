package com.supera.lista_tarefa.controllers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.supera.lista_tarefa.dtos.listatarefa.ListaComTarefaDTO;
import com.supera.lista_tarefa.dtos.listatarefa.ListaTarefaDTO;
import com.supera.lista_tarefa.services.ListaTarefaService;

@WebMvcTest(ListaTarefaController.class)
public class ListaTarefaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ListaTarefaService service;

    @MockBean
    private PagedResourcesAssembler<ListaTarefaDTO> assembler;

    @Test
    public void testGetAllListas() throws Exception {
        ListaTarefaDTO listaDTO = new ListaTarefaDTO(1L, "Lista 1");
        Page<ListaTarefaDTO> page = new PageImpl<>(List.of(listaDTO));
        PagedModel<EntityModel<ListaTarefaDTO>> pagedModel = assembler.toModel(page);

        when(service.findAll(0, 10)).thenReturn(page);
        when(assembler.toModel(page)).thenReturn(pagedModel);

        mockMvc.perform(MockMvcRequestBuilders.get("/listas-tarefas")
                .param("page", "0")
                .param("pageSize", "10")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$._embedded.listaTarefaDTOList[0].id").value(1L))
                .andExpect(jsonPath("$._embedded.listaTarefaDTOList[0].titulo").value("Lista 1"));

        verify(service, times(1)).findAll(0, 10);
    }
    @Test
    public void testGetListaById() throws Exception {
        ListaComTarefaDTO listaDTO = new ListaComTarefaDTO(1L, "Lista 1", Set.of());

        when(service.findById(1L)).thenReturn(listaDTO);

        mockMvc.perform(MockMvcRequestBuilders.get("/listas-tarefas/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.titulo").value("Lista 1"));

        verify(service, times(1)).findById(1L);
    }
    @Test
    public void testCreateListaTarefas() throws Exception {
        ListaTarefaDTO dto = new ListaTarefaDTO(1L, "Lista 1");
        ListaComTarefaDTO createdListaDTO = new ListaComTarefaDTO(1L, "Lista 1", Set.of());

        when(service.createListaTarefas(dto)).thenReturn(createdListaDTO);

        mockMvc.perform(MockMvcRequestBuilders.post("/listas-tarefas/adicionar")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\": 1, \"titulo\": \"Lista 1\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.titulo").value("Lista 1"));

        verify(service, times(1)).createListaTarefas(dto);
    }
    @Test
    public void testUpdateListaTarefas() throws Exception {
        ListaComTarefaDTO updatedListaDTO = new ListaComTarefaDTO(1L, "Lista Atualizada", Set.of());

        when(service.updateListaTarefas(eq(1L), any(ListaComTarefaDTO.class))).thenReturn(updatedListaDTO);

        mockMvc.perform(MockMvcRequestBuilders.put("/listas-tarefas/atualizar/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\": 1, \"titulo\": \"Lista Atualizada\", \"tarefas\": []}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.titulo").value("Lista Atualizada"));

        verify(service, times(1)).updateListaTarefas(eq(1L), any(ListaComTarefaDTO.class));
    }
    @Test
    public void testDeleteListaTarefa() throws Exception {
        doNothing().when(service).deleteListaTarefa(1L);

        mockMvc.perform(MockMvcRequestBuilders.delete("/listas-tarefas/deletar/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        verify(service, times(1)).deleteListaTarefa(1L);
    }
}
