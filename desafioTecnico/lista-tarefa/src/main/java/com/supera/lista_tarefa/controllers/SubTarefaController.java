package com.supera.lista_tarefa.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.supera.lista_tarefa.dtos.subtarefas.SubTarefaDTO;
import com.supera.lista_tarefa.services.SubTarefaService;

@RestController
@RequestMapping("/sub-tarefas")
public class SubTarefaController {

    private final SubTarefaService subTarefaService;

    public SubTarefaController(SubTarefaService subTarefaService) {
        this.subTarefaService = subTarefaService;
    }

    @GetMapping("/{id}")
    public SubTarefaDTO getById(@PathVariable Long id) {
        return subTarefaService.findById(id);
    }

    @PutMapping("/{id}")
    public SubTarefaDTO updateSubTarefaById(@PathVariable Long id, @RequestBody SubTarefaDTO subTarefaRecordDTO) {
        return subTarefaService.updateSubTarefa(subTarefaRecordDTO, id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        subTarefaService.deleteSubTarefaById(id);
        return ResponseEntity.noContent().build();
    }
}
