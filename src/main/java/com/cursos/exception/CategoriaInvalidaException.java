package com.cursos.exception;

import com.cursos.util.CursoConstants;

public class CategoriaInvalidaException extends CursoApplicationException {
    private Integer codigoCategoria;

    public CategoriaInvalidaException(Integer codigoCategoria) {
        this.codigoCategoria = codigoCategoria;
    }

    @Override
    public String getMessage() {
        return String.format(CursoConstants.ExceptionMessages.CATEGORIA_INVALIDA, this.codigoCategoria);
    }
}
