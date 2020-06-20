package com.cursos.service;

import com.cursos.exception.CategoriaInvalidaException;
import com.cursos.exception.CursoAlreadyExistException;
import com.cursos.exception.CursoNotFoundException;
import com.cursos.exception.InvalidPeriodException;
import com.cursos.model.Curso;
import com.cursos.repository.CursoRepository;
import com.cursos.util.ValidatorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.cursos.util.CursoConstants.ExceptionMessages.*;

@Service
public class CursoService {

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private ValidatorUtils validatorUtils;

    public Curso getCursoById(Integer id) throws CursoNotFoundException {
        Optional<Curso> cursoOptional = cursoRepository.findById(id);

        return cursoOptional.orElseThrow(()
                -> new CursoNotFoundException(String.format(CURSO_ID_NAO_ENCONTRADO, id)));
    }

    public Curso getCursoByDescricao(String descricao) throws CursoNotFoundException {
        Optional<Curso> cursoOptional = cursoRepository.findByDescricao(descricao);

        return cursoOptional.orElseThrow(()
                -> new CursoNotFoundException(String.format(CURSO_DESCRICAO_NAO_ENCONTRADO, descricao)));
    }

    public List<Curso> getAllCursos() throws CursoNotFoundException {
        List<Curso> cursoList = cursoRepository.findAll();

        if(cursoList == null || cursoList.size() == 0) {
            throw new CursoNotFoundException(NENHUM_CURSO_EXISTENTE);
        }

        return cursoList;
    }

    public Curso addCurso(Curso curso) throws CursoNotFoundException, InvalidPeriodException, CategoriaInvalidaException, CursoAlreadyExistException {
        curso.setId(null);

        validatorUtils.validateCurso(curso);
        validatorUtils.validateCategoria(curso);

        curso = cursoRepository.save(curso);
        return curso;
    }

    public void updateCurso(Integer id, Curso cursoAtualizado) throws CursoNotFoundException, InvalidPeriodException, CategoriaInvalidaException {
        Optional<Curso> curso = cursoRepository.findById(id);

        if(!curso.isPresent())
            throw new CursoNotFoundException(String.format(CURSO_ID_NAO_ENCONTRADO, id));

        validatorUtils.validateDatasDoCurso(cursoAtualizado);
        validatorUtils.validateCursoNoMesmoPeriodo(id, cursoAtualizado);
        validatorUtils.validateCategoria(cursoAtualizado);

        cursoAtualizado.setId(id);

        cursoRepository.save(cursoAtualizado);
    }

    public void deleteCurso(Integer id) throws CursoNotFoundException {
        if(cursoRepository.findId(id) == null)
            throw new CursoNotFoundException(String.format(CURSO_ID_NAO_ENCONTRADO, id));

        cursoRepository.deleteById(id);
    }






}
