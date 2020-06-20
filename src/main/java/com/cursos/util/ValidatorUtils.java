package com.cursos.util;

import com.cursos.exception.CategoriaInvalidaException;
import com.cursos.exception.CursoAlreadyExistException;
import com.cursos.exception.InvalidPeriodException;
import com.cursos.model.Curso;
import com.cursos.repository.CursoRepository;
import com.cursos.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static com.cursos.util.CursoConstants.ExceptionMessages.DATA_INVALIDA;
import static com.cursos.util.CursoConstants.ExceptionMessages.PERIODO_EXISTENTE;

@Component
public class ValidatorUtils {

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private CategoriaService categoriaService;

    private ValidatorUtils() {
    }

    public void validateCurso(Curso curso) throws InvalidPeriodException, CursoAlreadyExistException {
        validateCursoJaExistente(curso);
        validateDatasDoCurso(curso);
        validateCursoNoMesmoPeriodo(curso);
    }

    public void validateCursoJaExistente(Curso curso) throws CursoAlreadyExistException {
        Optional<Curso> cursoOptional = cursoRepository.findByDescricao(curso.getDescricao());

        if(cursoOptional.isPresent())
            throw new CursoAlreadyExistException(curso.getDescricao());

    }

    public void validateCursoNoMesmoPeriodo(Curso curso) throws InvalidPeriodException {
        LocalDate dataInicio = curso.getDataInicio();
        LocalDate dataTermino = curso.getDataTermino();
        List<Integer> cursoIds = cursoRepository.findCursoComMesmoPeriodo(dataInicio, dataTermino);

        if(cursoIds != null && !cursoIds.isEmpty())
            throw new InvalidPeriodException(PERIODO_EXISTENTE);
    }

    public void validateCursoNoMesmoPeriodo(Integer id, Curso curso) throws InvalidPeriodException {
        LocalDate dataInicio = curso.getDataInicio();
        LocalDate dataTermino = curso.getDataTermino();
        List<Integer> cursoIds = cursoRepository.findCursoComMesmoPeriodo(id, dataInicio, dataTermino);

        if(cursoIds != null && !cursoIds.isEmpty())
            throw new InvalidPeriodException(PERIODO_EXISTENTE);
    }

    public void validateDatasDoCurso(Curso curso) throws InvalidPeriodException {
        LocalDate dataInicio = curso.getDataInicio();
        LocalDate dataTermino = curso.getDataTermino();

        if(dataInicio.isAfter(dataTermino))
            throw new InvalidPeriodException(String.format(DATA_INVALIDA, dataInicio.toString(), dataTermino.toString()));
    }

    public void validateCategoria(Curso curso) throws CategoriaInvalidaException {
        Integer codigoCategoria = curso.getCategoria().getCodigo();
        if(!categoriaService.isValidCategoria(codigoCategoria))
            throw new CategoriaInvalidaException(codigoCategoria);
    }
}
