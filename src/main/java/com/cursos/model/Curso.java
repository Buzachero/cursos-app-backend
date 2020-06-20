package com.cursos.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.time.LocalDate;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
public class Curso {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty
    private String descricao;

    @JsonFormat(pattern="dd/MM/yyyy")
    @Column(name = "DATA_INICIO", columnDefinition = "DATE")
    @NotNull
    private LocalDate dataInicio;

    @JsonFormat(pattern="dd/MM/yyyy")
    @Column(name = "DATA_TERMINO", columnDefinition = "DATE")
    @NotNull
    private LocalDate dataTermino;

    @Column(name = "QNT_ALUNOS")
    private Integer quantidadeAlunos;

    @ManyToOne
    @JoinColumn(name = "CATEGORIA_CODIGO")
    @NotNull
    private Categoria categoria;

    public Curso() {
    }

    public Curso(Integer id, String descricao, LocalDate dataInicio, LocalDate dataTermino, Integer quantidadeAlunos, Categoria categoria) {
        this.id = id;
        this.descricao = descricao;
        this.dataInicio = dataInicio;
        this.dataTermino = dataTermino;
        this.quantidadeAlunos = quantidadeAlunos;
        this.categoria = categoria;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDate getDataTermino() {
        return dataTermino;
    }

    public void setDataTermino(LocalDate dataTermino) {
        this.dataTermino = dataTermino;
    }

    public Integer getQuantidadeAlunos() {
        return quantidadeAlunos;
    }

    public void setQuantidadeAlunos(Integer quantidadeAlunos) {
        this.quantidadeAlunos = quantidadeAlunos;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    @Override
    public String toString() {
        return "Curso{" +
                "id='" + id + '\'' +
                ", descricao='" + descricao + '\'' +
                ", dataInicio=" + dataInicio +
                ", dataTermino=" + dataTermino +
                ", quantidadeAlunos=" + quantidadeAlunos +
                ", categoria=" + categoria +
                '}';
    }
}
