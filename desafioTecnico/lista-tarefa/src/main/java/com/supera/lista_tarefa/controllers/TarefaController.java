package com.supera.lista_tarefa.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.supera.lista_tarefa.dtos.tarefa.TarefaComSubDTO;
import com.supera.lista_tarefa.dtos.tarefa.TarefaDTO;
import com.supera.lista_tarefa.model.Tarefa;
import com.supera.lista_tarefa.services.TarefaService;

@RestController
@RequestMapping("/tarefas")
public class TarefaController {

	@Autowired
    private TarefaService tarefaService;
	
	@GetMapping
    public PagedModel<EntityModel<TarefaDTO>> getAllTarefas(
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer pageSize,
            @RequestParam(required = false) Boolean concluida,
            @RequestParam(required = false) Boolean favorita,
            PagedResourcesAssembler<TarefaDTO> assembler)
    {
        Page<TarefaDTO> tarefaPage = tarefaService.findAll(page, pageSize, concluida, favorita);
        return assembler.toModel(tarefaPage);
    }
	
	@GetMapping("/{id}")
    public TarefaComSubDTO getTarefaById(@PathVariable Long id) {
        return tarefaService.findById(id);
    }
	
	@PostMapping("/criar")
	public ResponseEntity<TarefaComSubDTO> createTarefa(@RequestBody TarefaDTO dto) {
		// Chama o serviço para criar a Lista de Tarefas
		TarefaComSubDTO createdTarefa = tarefaService.createTarefa(dto);

		// Retorna a resposta com o status 201 Created e o DTO da Lista de Tarefas
		// criada
		return ResponseEntity.status(HttpStatus.CREATED).body(createdTarefa);
	}
	
    @PutMapping("/atualizar/{id}")
    public ResponseEntity<TarefaDTO> updateTarefa(@PathVariable Long id, @RequestBody TarefaComSubDTO tarefaDTO) {
        try {
            Tarefa updatedTarefa = tarefaService.updateTarefa(id, tarefaDTO);
            return ResponseEntity.ok(new TarefaDTO(updatedTarefa));
        } catch(RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    // Endpoint para deletar uma tarefa
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deleteTarefa(@PathVariable Long id) {
        tarefaService.deleteTarefa(id);
        return ResponseEntity.noContent().build();
    }
	
}
