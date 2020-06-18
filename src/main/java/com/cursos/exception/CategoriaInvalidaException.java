package com.cursos.exception;

import com.cursos.util.CursoConstants;

public class CategoriaInvalidaException extends Exception {
    private Integer categoria;

    public CategoriaInvalidaException(Integer categoria) {
        this.categoria = categoria;
    }

    @Override
    public String getMessage() {
        return String.format(CursoConstants.ExceptionMessages.CATEGORIA_INVALIDA, this.categoria);
    }
}
