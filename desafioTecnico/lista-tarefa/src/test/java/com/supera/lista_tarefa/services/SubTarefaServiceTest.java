package com.supera.lista_tarefa.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.supera.lista_tarefa.dtos.subtarefas.SubTarefaDTO;
import com.supera.lista_tarefa.model.SubTarefa;
import com.supera.lista_tarefa.repositories.SubTarefaRepository;

@SpringBootTest
public class SubTarefaServiceTest {

    @Mock
    private SubTarefaRepository subTarefaRepository;

    @InjectMocks
    private SubTarefaService subTarefaService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

   

    @Test
    void testFindByIdNotFound() {
        // Given
        Long id = 1L;
        when(subTarefaRepository.findById(id)).thenReturn(Optional.empty());

        // When & Then
        RuntimeException thrown = assertThrows(RuntimeException.class, () -> subTarefaService.findById(id));
        assertEquals("Sub-Tarefa de ID '1' não foi encontrada.", thrown.getMessage());
    }

    

    @Test
    void testUpdateSubTarefaNotFound() {
        // Given
        Long id = 1L;
        SubTarefaDTO dto = SubTarefaDTO.builder().nome("Updated Name").build();
        when(subTarefaRepository.findById(id)).thenReturn(Optional.empty());

        // When & Then
        RuntimeException thrown = assertThrows(RuntimeException.class, () -> subTarefaService.updateSubTarefa(dto, id));
        assertEquals("Sub-Tarefa de ID '1' não foi encontrada.", thrown.getMessage());
    }

    
    @Test
    void testDeleteSubTarefaByIdNotFound() {
        // Given
        Long id = 1L;
        when(subTarefaRepository.findById(id)).thenReturn(Optional.empty());

        // When & Then
        RuntimeException thrown = assertThrows(RuntimeException.class, () -> subTarefaService.deleteSubTarefaById(id));
        assertEquals("Sub-Tarefa de ID '1' não foi encontrada.", thrown.getMessage());
    }
}
