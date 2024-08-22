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

import com.supera.lista_tarefa.dtos.ListaComTarefaDTO;
import com.supera.lista_tarefa.dtos.ListaTarefaDTO;
import com.supera.lista_tarefa.services.ListaTarefaService;

@RestController
@RequestMapping("/listas-tarefas")
public class ListaTarefaController {

	@Autowired
	private ListaTarefaService service;

	@GetMapping
	public PagedModel<EntityModel<ListaTarefaDTO>> getAllListas(@RequestParam(required = false) Integer page,
			@RequestParam(required = false) Integer pageSize, PagedResourcesAssembler<ListaTarefaDTO> assembler) {
		Page<ListaTarefaDTO> listaPage = service.findAll(page, pageSize);
		return assembler.toModel(listaPage);
	}

	@GetMapping("/{id}")
	public ListaComTarefaDTO getListaById(@PathVariable Long id) {
		return service.findById(id);
	}

	@PostMapping("/adicionar")
	public ResponseEntity<ListaComTarefaDTO> createListaTarefas(@RequestBody ListaTarefaDTO listaTarefaDTO) {
		// Chama o serviço para criar a Lista de Tarefas
		ListaComTarefaDTO createdListaTarefa = service.createListaTarefas(listaTarefaDTO);

		// Retorna a resposta com o status 201 Created e o DTO da Lista de Tarefas
		// criada
		return ResponseEntity.status(HttpStatus.CREATED).body(createdListaTarefa);
	}
	
	// Endpoint para atualizar lista de tarefas
    @PutMapping("/atualizar/{id}")
    public ResponseEntity<ListaComTarefaDTO> updateListaTarefas(@PathVariable Long id,
                                                                 @RequestBody ListaComTarefaDTO listaComTarefasDTO)
    {
        try {
        	ListaComTarefaDTO listaAtualizada = service.updateListaTarefas(id, listaComTarefasDTO);
            return ResponseEntity.ok(listaAtualizada);
        } catch(RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
	
    // Endpoint para deletar lista de tarefas
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deleteListaTarefa(@PathVariable Long id) {
        service.deleteListaTarefa(id);
        return ResponseEntity.noContent().build();
    }

}
