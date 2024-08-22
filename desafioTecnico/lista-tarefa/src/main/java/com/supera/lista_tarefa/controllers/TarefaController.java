package com.supera.lista_tarefa.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.supera.lista_tarefa.dtos.tarefa.TarefaDTO;
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
	
}
