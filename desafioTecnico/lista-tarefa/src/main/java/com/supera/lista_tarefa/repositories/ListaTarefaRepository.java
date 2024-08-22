package com.supera.lista_tarefa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.supera.lista_tarefa.model.ListaTarefa;

public interface ListaTarefaRepository extends JpaRepository<ListaTarefa, Long>{

}
