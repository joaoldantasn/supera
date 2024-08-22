package com.supera.lista_tarefa.services;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.supera.lista_tarefa.dtos.mapper.TarefaSubDTOMapper;
import com.supera.lista_tarefa.dtos.subtarefas.SubTarefaDTO;
import com.supera.lista_tarefa.dtos.tarefa.TarefaComSubDTO;
import com.supera.lista_tarefa.dtos.tarefa.TarefaDTO;
import com.supera.lista_tarefa.model.SubTarefa;
import com.supera.lista_tarefa.model.Tarefa;
import com.supera.lista_tarefa.repositories.SubTarefaRepository;
import com.supera.lista_tarefa.repositories.TarefaRepository;

@Service
public class TarefaService {

	private final TarefaRepository repository;
	private final TarefaSubDTOMapper tarefaSubDTOMapper;
	private final SubTarefaRepository subTarefaRepository;

	public TarefaService(TarefaRepository repository, TarefaSubDTOMapper tarefaSubDTOMapper,
			SubTarefaRepository subTarefaRepository) {
		this.repository = repository;
		this.tarefaSubDTOMapper = tarefaSubDTOMapper;
		this.subTarefaRepository = subTarefaRepository;
	}

	@Transactional(readOnly = true)
	public Page<TarefaDTO> findAll(Integer page, Integer pageSize, Boolean concluida, Boolean favorita) {
		page = Optional.ofNullable(page).orElse(0);
		pageSize = Math.min(Optional.ofNullable(pageSize).orElse(6), 15);

		Pageable pageable = PageRequest.of(page, pageSize);
		Page<Tarefa> tarefaPage;
		if (concluida != null) {
			tarefaPage = repository.findByConcluida(concluida, pageable);
		} else if (favorita != null) {
			tarefaPage = repository.findByFavorita(favorita, pageable);
		} else {
			tarefaPage = repository.findAll(pageable);
		}

		return tarefaPage.map(TarefaDTO::new);
	}

	@Transactional(readOnly = true)
	public TarefaComSubDTO findById(Long id) {
		// Procura pela tarefa, se nao acha lança exception
		Tarefa tarefa = repository.findById(id)
				.orElseThrow(() -> new RuntimeException(String.format("Tarefa de ID '%d' não foi encontrada.", id)));
		// Mapea a tarefa para DTO no serviço de mapeador
		return tarefaSubDTOMapper.apply(tarefa);
	}
	
	@Transactional
    public TarefaComSubDTO createTarefa(TarefaDTO dto) {
        
        Tarefa tarefa = new Tarefa();
        tarefa.setTitulo(dto.titulo());
        tarefa.setDescricao(dto.descricao());
        tarefa.setDataConclusao(dto.dataConclusao());
        tarefa.setDataPrevistaConclucao(dto.dataPrevistaConclucao());
        tarefa.setFavorita(dto.favorita());
        tarefa.setConcluida(dto.concluida());

        tarefa = repository.save(tarefa);
        return tarefaSubDTOMapper.apply(tarefa);
    }
	
	public Tarefa updateTarefa(Long id, TarefaComSubDTO tarefaDTO) {
	    // Verifica se a tarefa existe
	    Tarefa tarefaParaSerAtualizado = repository.findById(id).orElseThrow(
	            () -> new RuntimeException(String.format("Tarefa de ID '%d' não foi encontrada.", id))
	    );

	    // Atualiza os campos da tarefa
	    tarefaParaSerAtualizado.setTitulo(tarefaDTO.titulo());
	    tarefaParaSerAtualizado.setDescricao(tarefaDTO.descricao());
	    tarefaParaSerAtualizado.setDataPrevistaConclucao(tarefaDTO.dataPrevistaConclucao());
	    tarefaParaSerAtualizado.setConcluida(tarefaDTO.concluida());
	    tarefaParaSerAtualizado.setFavorita(tarefaDTO.favorita());

	    if(tarefaDTO.concluida()) {
	        tarefaParaSerAtualizado.setDataConclusao(LocalDate.now());
	    } else {
	        tarefaParaSerAtualizado.setDataConclusao(null);
	    }

	    // Atualiza as subtarefas
	    Set<SubTarefa> subtarefas = new HashSet<>();
	    for (SubTarefaDTO subTarefaDTO : tarefaDTO.subtarefas()) {
	        SubTarefa subTarefa;
	        if (subTarefaDTO.id() != null) {
	            Optional<SubTarefa> existingSubTarefaOpt = subTarefaRepository.findById(subTarefaDTO.id());
	            if (existingSubTarefaOpt.isPresent()) {
	                subTarefa = existingSubTarefaOpt.get();
	                subTarefa.setNome(subTarefaDTO.nome());
	            } else {
	                throw new RuntimeException("Subtarefa não encontrada com ID: " + subTarefaDTO.id());
	            }
	        } else {
	            subTarefa = new SubTarefa();
	            subTarefa.setNome(subTarefaDTO.nome());
	            subTarefa.setTarefa(tarefaParaSerAtualizado);
	        }
	        subtarefas.add(subTarefa);
	    }
	    tarefaParaSerAtualizado.setSubtarefas(subtarefas);

	    // Salva a tarefa atualizada
	    return repository.save(tarefaParaSerAtualizado);
	}
	@Transactional
	public void deleteTarefa(Long id) {
        // Procura por ID e verifica se esta presente, se estiver deleta e se nao estiver lança exception
        repository.findById(id).ifPresentOrElse(
                repository::delete, () -> {
                    throw new RuntimeException(String.format("Tarefa de ID '%d' não foi encontrada.", id));
                }
        );
    }


}
