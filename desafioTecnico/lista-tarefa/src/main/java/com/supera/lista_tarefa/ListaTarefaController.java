package com.supera.lista_tarefa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.supera.lista_tarefa.dtos.ListaTarefaRecordDTO;
import com.supera.lista_tarefa.services.ListaTarefaService;

@RestController
@RequestMapping("/listas-tarefas")
public class ListaTarefaController {
	

	@Autowired
	private ListaTarefaService service;
	
	@GetMapping
	public PagedModel<EntityModel<ListaTarefaRecordDTO>> getAllListas(
			@RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer pageSize,
			PagedResourcesAssembler<ListaTarefaRecordDTO>assembler){
		Page<ListaTarefaRecordDTO> listaPage = service.findAll(page, pageSize);
		return assembler.toModel(listaPage);
	}

}
