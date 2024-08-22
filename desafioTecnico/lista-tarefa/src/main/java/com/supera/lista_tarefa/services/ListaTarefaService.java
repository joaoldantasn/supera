package com.supera.lista_tarefa.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.supera.lista_tarefa.dtos.ListaTarefaRecordDTO;
import com.supera.lista_tarefa.model.ListaTarefa;
import com.supera.lista_tarefa.repositories.ListaTarefaRepository;



@Service
public class ListaTarefaService {

	@Autowired
	private ListaTarefaRepository repository;
	
	
	@Transactional(readOnly = true)
	public Page<ListaTarefaRecordDTO> findAll(Integer page, Integer pageSize) {
	    page = Optional.ofNullable(page).orElse(0);
	    pageSize = Math.min(Optional.ofNullable(pageSize).orElse(6), 15);

	    Pageable pageable = PageRequest.of(page, pageSize);
	    Page<ListaTarefa> listaTarefaPage = repository.findAll(pageable);

	    return listaTarefaPage.map(ListaTarefaRecordDTO::new);
	}

	
}
