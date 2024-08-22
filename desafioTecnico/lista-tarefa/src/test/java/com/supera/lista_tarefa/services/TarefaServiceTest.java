package com.supera.lista_tarefa.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.supera.lista_tarefa.dtos.mapper.TarefaSubDTOMapper;
import com.supera.lista_tarefa.dtos.subtarefas.SubTarefaDTO;
import com.supera.lista_tarefa.dtos.tarefa.TarefaComSubDTO;
import com.supera.lista_tarefa.dtos.tarefa.TarefaDTO;
import com.supera.lista_tarefa.model.SubTarefa;
import com.supera.lista_tarefa.model.Tarefa;
import com.supera.lista_tarefa.repositories.SubTarefaRepository;
import com.supera.lista_tarefa.repositories.TarefaRepository;

import jakarta.validation.constraints.AssertFalse.List;

@ExtendWith(MockitoExtension.class)
public class TarefaServiceTest {

    @Mock
    private TarefaRepository repository;

    @Mock
    private TarefaSubDTOMapper tarefaSubDTOMapper;

    @Mock
    private SubTarefaRepository subTarefaRepository;

    @InjectMocks
    private TarefaService tarefaService;

    private Tarefa tarefa;
    private TarefaComSubDTO tarefaDTO;
    private SubTarefa subTarefa;
    private SubTarefaDTO subTarefaDTO;

    @BeforeEach
    void setup() {
        // Criação de mocks e objetos de teste
        subTarefaDTO = new SubTarefaDTO(1L, "Subtarefa Teste");
        Set<SubTarefaDTO> subtarefaDTOs = new HashSet<>();
        subtarefaDTOs.add(subTarefaDTO);

        tarefaDTO = new TarefaComSubDTO(1L, "Tarefa Teste", "Descrição Teste", LocalDate.now(), LocalDate.now().plusDays(1), false, false, subtarefaDTOs);

        subTarefa = new SubTarefa();
        subTarefa.setId(1L);
        subTarefa.setNome("Subtarefa Teste");

        tarefa = new Tarefa();
        tarefa.setId(1L);
        tarefa.setTitulo("Tarefa Teste");
        tarefa.setDescricao("Descrição Teste");
        tarefa.setSubtarefas(new HashSet<>());
    }


    @Test
    void testFindById() {
        // Configura o repositório mock para retornar uma tarefa
        when(repository.findById(1L)).thenReturn(Optional.of(tarefa));
        when(tarefaSubDTOMapper.apply(tarefa)).thenReturn(tarefaDTO);

        TarefaComSubDTO result = tarefaService.findById(1L);

        // Verificações
        assertNotNull(result);
        assertEquals("Tarefa Teste", result.titulo());
        verify(repository, times(1)).findById(1L);
    }

    @Test
    void testUpdateTarefa() {
        // Configura o repositório mock para encontrar e atualizar uma tarefa
        when(repository.findById(1L)).thenReturn(Optional.of(tarefa));
        when(subTarefaRepository.findById(1L)).thenReturn(Optional.of(subTarefa));
        when(repository.save(any(Tarefa.class))).thenReturn(tarefa);

        Tarefa result = tarefaService.updateTarefa(1L, tarefaDTO);

        // Verificações
        assertNotNull(result);
        assertEquals("Tarefa Teste", result.getTitulo());
        verify(repository, times(1)).findById(1L);
        verify(repository, times(1)).save(any(Tarefa.class));
    }

    @Test
    void testDeleteTarefa() {
        // Configura o repositório mock para encontrar e deletar uma tarefa
        when(repository.findById(1L)).thenReturn(Optional.of(tarefa));

        tarefaService.deleteTarefa(1L);

        // Verificações
        verify(repository, times(1)).findById(1L);
        verify(repository, times(1)).delete(tarefa);
    }
}