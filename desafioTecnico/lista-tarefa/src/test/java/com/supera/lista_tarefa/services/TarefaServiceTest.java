package com.supera.lista_tarefa.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import com.supera.lista_tarefa.dtos.mapper.TarefaSubDTOMapper;
import com.supera.lista_tarefa.dtos.subtarefas.SubTarefaDTO;
import com.supera.lista_tarefa.dtos.tarefa.TarefaComSubDTO;
import com.supera.lista_tarefa.dtos.tarefa.TarefaDTO;
import com.supera.lista_tarefa.model.SubTarefa;
import com.supera.lista_tarefa.model.Tarefa;
import com.supera.lista_tarefa.repositories.SubTarefaRepository;
import com.supera.lista_tarefa.repositories.TarefaRepository;

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
    void testCreateTarefa() {
        // Arrange
        TarefaDTO tarefaDTO = TarefaDTO.builder()
                .titulo("Nova Tarefa")
                .descricao("Descrição da nova tarefa")
                .dataConclusao(LocalDate.now().plusDays(1))
                .dataPrevistaConclucao(LocalDate.now().plusDays(2))
                .concluida(false)
                .favorita(false)
                .build();

        Tarefa tarefa = new Tarefa();
        tarefa.setTitulo(tarefaDTO.titulo());
        tarefa.setDescricao(tarefaDTO.descricao());
        tarefa.setDataConclusao(tarefaDTO.dataConclusao());
        tarefa.setDataPrevistaConclucao(tarefaDTO.dataPrevistaConclucao());
        tarefa.setConcluida(tarefaDTO.concluida());
        tarefa.setFavorita(tarefaDTO.favorita());

        Tarefa savedTarefa = new Tarefa();
        savedTarefa.setId(1L);
        savedTarefa.setTitulo(tarefaDTO.titulo());
        savedTarefa.setDescricao(tarefaDTO.descricao());
        savedTarefa.setDataConclusao(tarefaDTO.dataConclusao());
        savedTarefa.setDataPrevistaConclucao(tarefaDTO.dataPrevistaConclucao());
        savedTarefa.setConcluida(tarefaDTO.concluida());
        savedTarefa.setFavorita(tarefaDTO.favorita());

        TarefaComSubDTO tarefaComSubDTO = TarefaComSubDTO.builder()
                .id(savedTarefa.getId())
                .titulo(savedTarefa.getTitulo())
                .descricao(savedTarefa.getDescricao())
                .dataConclusao(savedTarefa.getDataConclusao())
                .dataPrevistaConclucao(savedTarefa.getDataPrevistaConclucao())
                .concluida(savedTarefa.isConcluida())
                .favorita(savedTarefa.isFavorita())
                .build();

        // Mockando o comportamento do repositório e do mapper
        when(repository.save(any(Tarefa.class))).thenReturn(savedTarefa);
        when(tarefaSubDTOMapper.apply(any(Tarefa.class))).thenReturn(tarefaComSubDTO);

        // Act
        TarefaComSubDTO result = tarefaService.createTarefa(tarefaDTO);

        // Assert
        assertEquals(tarefaComSubDTO, result);
        assertEquals(tarefaDTO.titulo(), result.titulo());
        assertEquals(tarefaDTO.descricao(), result.descricao());
        assertEquals(tarefaDTO.dataConclusao(), result.dataConclusao());
        assertEquals(tarefaDTO.dataPrevistaConclucao(), result.dataPrevistaConclucao());
        assertEquals(tarefaDTO.concluida(), result.concluida());
        assertEquals(tarefaDTO.favorita(), result.favorita());
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
    
  
    @Test
    public void testFindAll_WithConcluidaFilter() {
        // Dados de exemplo
        List<Tarefa> tarefas = List.of(
            new Tarefa(1L, "Tarefa 1", "Descricao 1", null, null, true, false),
            new Tarefa(2L, "Tarefa 2", "Descricao 2", null, null, true, false)
        );
        Page<Tarefa> page = new PageImpl<>(tarefas);

        Mockito.when(repository.findByConcluida(true, PageRequest.of(0, 6))).thenReturn(page);

        // Execução do método
        Page<TarefaDTO> result = tarefaService.findAll(0, 6, true, null);

        // Verificação
        Assertions.assertEquals(2, result.getTotalElements());
        Assertions.assertTrue(result.getContent().get(0).concluida());
        Assertions.assertTrue(result.getContent().get(1).concluida());
    }
    
    @Test
    public void testFindAll_WithFavoritaFilter() {
        // Dados de exemplo
        List<Tarefa> tarefas = List.of(
            new Tarefa(1L, "Tarefa 1", "Descricao 1", null, null, false, true),
            new Tarefa(2L, "Tarefa 2", "Descricao 2", null, null, false, true)
        );
        Page<Tarefa> page = new PageImpl<>(tarefas);

        Mockito.when(repository.findByFavorita(true, PageRequest.of(0, 6))).thenReturn(page);

        // Execução do método
        Page<TarefaDTO> result = tarefaService.findAll(0, 6, null, true);

        // Verificação
        Assertions.assertEquals(2, result.getTotalElements());
        Assertions.assertTrue(result.getContent().get(0).favorita());
        Assertions.assertTrue(result.getContent().get(1).favorita());
    }
    
    @Test
    public void testFindAll_NoFilters() {
        // Dados de exemplo
        List<Tarefa> tarefas = List.of(
            new Tarefa(1L, "Tarefa 1", "Descricao 1", null, null, false, false),
            new Tarefa(2L, "Tarefa 2", "Descricao 2", null, null, false, false)
        );
        Page<Tarefa> page = new PageImpl<>(tarefas);

        Mockito.when(repository.findAll(PageRequest.of(0, 6))).thenReturn(page);

        // Execução do método
        Page<TarefaDTO> result = tarefaService.findAll(0, 6, null, null);

        // Verificação
        Assertions.assertEquals(2, result.getTotalElements());
    }
}