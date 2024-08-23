package com.supera.lista_tarefa.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.supera.lista_tarefa.dtos.listatarefa.ListaComTarefaDTO;
import com.supera.lista_tarefa.dtos.listatarefa.ListaTarefaDTO;
import com.supera.lista_tarefa.dtos.mapper.ListaTarefaDTOMapper;
import com.supera.lista_tarefa.dtos.tarefa.TarefaDTO;
import com.supera.lista_tarefa.model.ListaTarefa;
import com.supera.lista_tarefa.repositories.ListaTarefaRepository;
import com.supera.lista_tarefa.repositories.TarefaRepository;

class ListaTarefaServiceTest {

    @Mock
    private ListaTarefaRepository repository;

    @Mock
    private TarefaRepository tarefaRepository;

    @Mock
    private ListaTarefaDTOMapper listaTarefaDTOMapper;

    @InjectMocks
    private ListaTarefaService listaTarefaService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAll() {
        Pageable pageable = PageRequest.of(0, 10);
        ListaTarefa listaTarefa = new ListaTarefa(1L, "Lista 1");
        Page<ListaTarefa> page = new PageImpl<>(Collections.singletonList(listaTarefa));
        
        when(repository.findAll(pageable)).thenReturn(page);
        
        Page<ListaTarefaDTO> result = listaTarefaService.findAll(0, 10);
        
        assertEquals(1, result.getTotalElements());
        verify(repository, times(1)).findAll(pageable);
    }

    @Test
    void testFindById() {
        Long id = 1L;
        ListaTarefa listaTarefa = new ListaTarefa(id, "Lista 1");
        ListaComTarefaDTO listaComTarefaDTO = new ListaComTarefaDTO(id, "Lista 1", Collections.emptySet());

        when(repository.findById(id)).thenReturn(Optional.of(listaTarefa));
        when(listaTarefaDTOMapper.apply(listaTarefa)).thenReturn(listaComTarefaDTO);

        ListaComTarefaDTO result = listaTarefaService.findById(id);

        assertEquals("Lista 1", result.titulo());
        verify(repository, times(1)).findById(id);
    }

    @Test
    void testCreateListaTarefas() {
        ListaTarefaDTO listaTarefaDTO = new ListaTarefaDTO(null, "Nova Lista");
        ListaTarefa listaTarefa = new ListaTarefa(null, "Nova Lista");
        ListaComTarefaDTO listaComTarefaDTO = new ListaComTarefaDTO(1L, "Nova Lista", Collections.emptySet());

        when(repository.save(any(ListaTarefa.class))).thenReturn(listaTarefa);
        when(listaTarefaDTOMapper.apply(listaTarefa)).thenReturn(listaComTarefaDTO);

        ListaComTarefaDTO result = listaTarefaService.createListaTarefas(listaTarefaDTO);

        assertEquals("Nova Lista", result.titulo());
        verify(repository, times(1)).save(any(ListaTarefa.class));
    }

    @Test
    void testUpdateListaTarefas() {
        Long id = 1L;
        ListaTarefa existingLista = new ListaTarefa(id, "Lista Antiga");
        Set<TarefaDTO> tarefasDTOs = new HashSet<>();
        ListaComTarefaDTO listaComTarefaDTO = new ListaComTarefaDTO(id, "Lista Atualizada", tarefasDTOs);

        when(repository.findById(id)).thenReturn(Optional.of(existingLista));
        when(repository.save(existingLista)).thenReturn(existingLista);
        when(listaTarefaDTOMapper.apply(existingLista)).thenReturn(listaComTarefaDTO);

        ListaComTarefaDTO result = listaTarefaService.updateListaTarefas(id, listaComTarefaDTO);

        assertEquals("Lista Atualizada", result.titulo());
        verify(repository, times(1)).findById(id);
        verify(repository, times(1)).save(existingLista);
    }

    @Test
    void testDeleteListaTarefa() {
        Long id = 1L;
        
        doNothing().when(repository).deleteById(id);
        
        listaTarefaService.deleteListaTarefa(id);
        
        verify(repository, times(1)).deleteById(id);
    }
   

}
