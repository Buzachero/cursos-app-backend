package com.cursos.util;

public interface CursoConstants {

    public interface ExceptionMessages {
        String NENHUM_CURSO_EXISTENTE = "Não há nenhum curso cadastrado no momento";
        String CURSO_ID_NAO_ENCONTRADO = "Curso com id=%d não foi encontrado";
        String CURSO_DESCRICAO_NAO_ENCONTRADO = "Curso com descricao='%s'não foi encontrado";
        String DATA_INVALIDA = "Data inicial '%s' é posterior a data final '%s'";
        String PERIODO_EXISTENTE = "Existe(m) curso(s) planejados(s) dentro do período informado";
        String CATEGORIA_INVALIDA = "Categoria %d é inválida";
    }
}
