package com.supera.lista_tarefa.services;

import org.springframework.stereotype.Service;

import com.supera.lista_tarefa.dtos.subtarefas.SubTarefaDTO;
import com.supera.lista_tarefa.model.SubTarefa;
import com.supera.lista_tarefa.repositories.SubTarefaRepository;

@Service
public class SubTarefaService {

    private final SubTarefaRepository subTarefaRepository;

    public SubTarefaService(SubTarefaRepository subTarefaRepository) {
 
 		this.subTarefaRepository = subTarefaRepository;
 	}
    
    /*
     * GetById
     * Update
     * Delete
     */

    public SubTarefaDTO findById(Long id) {
        SubTarefa subTarefa = subTarefaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(String.format("Sub-Tarefa de ID '%d' não foi encontrada.", id)));
        return SubTarefaDTO.builder()
                .nome(subTarefa.getNome())
                .build();
    }

 

	public SubTarefaDTO updateSubTarefa(SubTarefaDTO subTarefaRecordDTO, Long id) {
        SubTarefa subTarefa = subTarefaRepository.findById(id).orElseThrow(
                () -> new RuntimeException(String.format("Sub-Tarefa de ID '%d' não foi encontrada.", id))
        );

        subTarefa.setNome(subTarefaRecordDTO.nome());

        SubTarefa subTarefaAtualizada = SubTarefa.builder()
                .id(subTarefa.getId())
                .nome(subTarefa.getNome())
                .tarefa(subTarefa.getTarefa())
                .build();

        subTarefaRepository.save(subTarefaAtualizada);

        return SubTarefaDTO.builder()
                .nome(subTarefaAtualizada.getNome())
                .build();
    }

    public void deleteSubTarefaById(Long id) {
        subTarefaRepository.findById(id).ifPresentOrElse(
                subTarefaRepository::delete, () -> {
                    throw new RuntimeException(String.format("Sub-Tarefa de ID '%d' não foi encontrada.", id));
                }
        );
    }
}
