package com.supera.lista_tarefa.services;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.supera.lista_tarefa.dtos.listatarefa.ListaComTarefaDTO;
import com.supera.lista_tarefa.dtos.listatarefa.ListaTarefaDTO;
import com.supera.lista_tarefa.dtos.mapper.ListaTarefaDTOMapper;
import com.supera.lista_tarefa.model.ListaTarefa;
import com.supera.lista_tarefa.model.Tarefa;
import com.supera.lista_tarefa.repositories.ListaTarefaRepository;
import com.supera.lista_tarefa.repositories.TarefaRepository;



@Service
public class ListaTarefaService {


	private ListaTarefaRepository repository;
	private final ListaTarefaDTOMapper listaTarefaDTOMapper;
	private TarefaRepository tarefaRepository;
	
	public ListaTarefaService(ListaTarefaRepository repository, ListaTarefaDTOMapper listaTarefaDTOMapper,
			TarefaRepository tarefaRepository) {
		this.repository = repository;
		this.listaTarefaDTOMapper = listaTarefaDTOMapper;
		this.tarefaRepository = tarefaRepository;
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
	
	@Transactional
	public ListaComTarefaDTO updateListaTarefas(Long id, ListaComTarefaDTO listaComTarefasDTO) {
	    // Busca a lista de tarefas existente pelo ID
	    ListaTarefa existingLista = repository.findById(id)
	            .orElseThrow(() -> new RuntimeException("Lista de tarefas não encontrada"));

	    // Atualiza o título da lista de tarefas
	    existingLista.setTitulo(listaComTarefasDTO.titulo());

	    // Atualiza ou cria novas tarefas associadas à lista
	    Set<Tarefa> updatedTarefas = listaComTarefasDTO.tarefas().stream().map(tarefaDTO -> {
	        if (tarefaDTO.id() != null) {
	            // Atualiza tarefa existente
	            Tarefa existingTarefa = tarefaRepository.findById(tarefaDTO.id())
	                    .orElseThrow(() -> new RuntimeException("Tarefa não encontrada"));

	            existingTarefa.setTitulo(tarefaDTO.titulo());
	            existingTarefa.setDescricao(tarefaDTO.descricao());
	            existingTarefa.setDataPrevistaConclucao(tarefaDTO.dataPrevistaConclucao());
	            existingTarefa.setConcluida(tarefaDTO.concluida());
	            existingTarefa.setFavorita(tarefaDTO.favorita());

	            return existingTarefa;
	        } else {
	            // Cria uma nova tarefa
	            return Tarefa.builder()
	                    .titulo(tarefaDTO.titulo())
	                    .descricao(tarefaDTO.descricao())
	                    .dataPrevistaConclucao(tarefaDTO.dataPrevistaConclucao())
	                    .concluida(tarefaDTO.concluida())
	                    .favorita(tarefaDTO.favorita())
	                    .listaTarefa(existingLista)
	                    .build();
	        }
	    }).collect(Collectors.toSet());

	    // Atualiza o conjunto de tarefas na lista existente
	    existingLista.getTarefas().clear();
	    existingLista.getTarefas().addAll(updatedTarefas);

	    // Salva as alterações na lista de tarefas
	    ListaTarefa listaAtualizada = repository.save(existingLista);

	    // Retorna o DTO da lista de tarefas atualizada
	    return listaTarefaDTOMapper.apply(listaAtualizada);
	}

	
	@Transactional
	public void deleteListaTarefa(Long id) {
		repository.deleteById(id);
	}
}


