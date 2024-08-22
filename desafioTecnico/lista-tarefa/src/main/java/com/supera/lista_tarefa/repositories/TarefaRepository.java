package com.supera.lista_tarefa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.supera.lista_tarefa.model.Tarefa;

public interface TarefaRepository extends JpaRepository<Tarefa, Long>{

}
