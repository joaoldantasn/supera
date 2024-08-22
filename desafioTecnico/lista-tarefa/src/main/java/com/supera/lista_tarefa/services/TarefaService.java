package com.supera.lista_tarefa.services;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.supera.lista_tarefa.dtos.tarefa.TarefaDTO;
import com.supera.lista_tarefa.model.Tarefa;
import com.supera.lista_tarefa.repositories.SubTarefaRepository;
import com.supera.lista_tarefa.repositories.TarefaRepository;

@Service
public class TarefaService {

	private final TarefaRepository repository;
	private final SubTarefaRepository subTarefaRepository;
	
	public TarefaService(TarefaRepository repository, SubTarefaRepository subTarefaRepository) {
		this.repository = repository;
		this.subTarefaRepository = subTarefaRepository;
	}
	
	@Transactional(readOnly = true)
    public Page<TarefaDTO> findAll(Integer page, Integer pageSize, Boolean concluida, Boolean favorita) {
        page = Optional.ofNullable(page).orElse(0);
        pageSize = Math.min(Optional.ofNullable(pageSize).orElse(6), 15);

        Pageable pageable = PageRequest.of(page, pageSize);
        Page<Tarefa> tarefaPage;
        if(concluida != null) {
            tarefaPage = repository.findByConcluida(concluida, pageable);
        }
        else if(favorita != null) {
            tarefaPage = repository.findByFavorita(favorita, pageable);
        }
        else {
            tarefaPage = repository.findAll(pageable);
        }

        return tarefaPage.map(TarefaDTO::new);
    }

	
}
