package com.supera.lista_tarefa.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.supera.lista_tarefa.model.Tarefa;

public interface TarefaRepository extends JpaRepository<Tarefa, Long>{

	Page<Tarefa> findByConcluida(boolean concluida, Pageable pageable);
    Page<Tarefa> findByFavorita(boolean favorita, Pageable pageable);
	
}
