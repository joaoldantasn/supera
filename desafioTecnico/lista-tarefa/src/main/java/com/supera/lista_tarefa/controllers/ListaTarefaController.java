package com.supera.lista_tarefa.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	public PagedModel<EntityModel<ListaTarefaDTO>> getAllListas(
			@RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer pageSize,
			PagedResourcesAssembler<ListaTarefaDTO>assembler){
		Page<ListaTarefaDTO> listaPage = service.findAll(page, pageSize);
		return assembler.toModel(listaPage);
	}
	
	@GetMapping("/{id}")
	public ListaComTarefaDTO getListaById(@PathVariable Long id) {
		return service.findById(id);
	}

}
