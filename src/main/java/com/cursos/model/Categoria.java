package com.cursos.model;

public enum Categoria {
    COMPORTAMENTAL(1, "Comportamental"),
    PROGRAMACAO(2, "Programacao"),
    QUALIDADE(3, "Qualidade"),
    PROCESSOS(4, "Processos");

    private Integer codigo;
    private String nome;

    Categoria(Integer codigo, String nome) {
        this.codigo = codigo;
        this.nome = nome;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }
}
