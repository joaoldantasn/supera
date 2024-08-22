package com.supera.lista_tarefa.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.supera.lista_tarefa.dtos.ListaComTarefaDTO;
import com.supera.lista_tarefa.dtos.ListaTarefaDTO;
import com.supera.lista_tarefa.dtos.mapper.ListaTarefaDTOMapper;
import com.supera.lista_tarefa.model.ListaTarefa;
import com.supera.lista_tarefa.repositories.ListaTarefaRepository;



@Service
public class ListaTarefaService {


	private ListaTarefaRepository repository;
	private final ListaTarefaDTOMapper listaTarefaDTOMapper;
	
	public ListaTarefaService(ListaTarefaRepository repository, ListaTarefaDTOMapper listaTarefaDTOMapper) {
		this.repository = repository;
		this.listaTarefaDTOMapper = listaTarefaDTOMapper;
	}

	@Transactional(readOnly = true)
	public Page<ListaTarefaDTO> findAll(Integer page, Integer pageSize) {
	    page = Optional.ofNullable(page).orElse(0);
	    pageSize = Math.min(Optional.ofNullable(pageSize).orElse(6), 15);

	    Pageable pageable = PageRequest.of(page, pageSize);
	    Page<ListaTarefa> listaTarefaPage = repository.findAll(pageable);

	    return listaTarefaPage.map(ListaTarefaDTO::new);
	}
	
	@Transactional(readOnly = true)
	public ListaComTarefaDTO findById(Long id) {
	    ListaTarefa listaTarefa = repository.findById(id)
	            .orElseThrow(() -> new RuntimeException(String.format("Lista de Tarefa de ID '%d' não foi encontrada.", id)));

	    return listaTarefaDTOMapper.apply(listaTarefa);
	}
	
	@Transactional
    public ListaComTarefaDTO createListaTarefas(ListaTarefaDTO dto) {
        
        ListaTarefa listaTarefa = new ListaTarefa();
        listaTarefa.setTitulo(dto.titulo());

        listaTarefa = repository.save(listaTarefa);
        return listaTarefaDTOMapper.apply(listaTarefa);
    }
}


