package com.supera.lista_tarefa.model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_tarefa")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Tarefa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Tarefa precisa de um nome")
    @Size(min = 5, message = "Precisa ter pelo menos 5 caracteres")
    private String titulo;

    @NotBlank
    private String descricao;

    @Column(name = "data_conclusao")
    private LocalDate dataConclusao;

    @Future
    @Column(name = "data_prevista")
    private LocalDate dataPrevistaConclucao;

    private boolean concluida;

    private boolean favorita;

    @ManyToOne
    @JoinColumn(name = "lista_tarefas_id")
    @JsonIgnore
    private ListaTarefa listaTarefa;

    @OneToMany(mappedBy = "tarefa", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<SubTarefa> subtarefas = new HashSet<>();
    
	public Tarefa(Long id,
			String titulo,
			String descricao, LocalDate dataConclusao,LocalDate dataPrevistaConclucao,
			boolean concluida, boolean favorita) {
		this.id = id;
		this.titulo = titulo;
		this.descricao = descricao;
		this.dataConclusao = dataConclusao;
		this.dataPrevistaConclucao = dataPrevistaConclucao;
		this.concluida = concluida;
		this.favorita = favorita;
	}
    

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public LocalDate getDataConclusao() {
		return dataConclusao;
	}

	public void setDataConclusao(LocalDate dataConclusao) {
		this.dataConclusao = dataConclusao;
	}

	public LocalDate getDataPrevistaConclucao() {
		return dataPrevistaConclucao;
	}

	public void setDataPrevistaConclucao(LocalDate dataPrevistaConclucao) {
		this.dataPrevistaConclucao = dataPrevistaConclucao;
	}

	public boolean isConcluida() {
		return concluida;
	}

	public void setConcluida(boolean concluida) {
		this.concluida = concluida;
	}

	public boolean isFavorita() {
		return favorita;
	}

	public void setFavorita(boolean favorita) {
		this.favorita = favorita;
	}

	public ListaTarefa getListaTarefa() {
		return listaTarefa;
	}

	public void setListaTarefa(ListaTarefa listaTarefa) {
		this.listaTarefa = listaTarefa;
	}

	public Set<SubTarefa> getSubtarefas() {
		return subtarefas;
	}

	public void setSubtarefas(Set<SubTarefa> subtarefas) {
		this.subtarefas = subtarefas;
	}
    
    
}
