package com.cursos.exception;

import com.cursos.util.CursoConstants;

public class CursoAlreadyExistException extends CursoApplicationException {
    private String descricaoCurso;

    public CursoAlreadyExistException(String descricaoCurso) {
        this.descricaoCurso = descricaoCurso;
    }

    @Override
    public String getMessage() {
        return String.format(CursoConstants.ExceptionMessages.CURSO_EXISTENTE, this.descricaoCurso);
    }
}
