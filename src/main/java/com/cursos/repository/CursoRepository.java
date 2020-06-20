package com.cursos.repository;

import com.cursos.model.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Integer> {

    @Query("SELECT curso.id FROM Curso curso WHERE curso.id = :cursoId")
    @Transactional(readOnly = true)
    Integer findId(Integer cursoId);

    @Transactional(readOnly = true)
    Optional<Curso> findByDescricao(String descricao);

    @Query("SELECT curso.id FROM Curso curso WHERE (:dataInicio BETWEEN curso.dataInicio AND curso.dataTermino) OR (:dataTermino BETWEEN curso.dataInicio AND curso.dataTermino)")
    @Transactional(readOnly = true)
    List<Integer> findCursoComMesmoPeriodo(LocalDate dataInicio, LocalDate dataTermino);

    @Query("SELECT curso.id FROM Curso curso WHERE curso.id != :cursoId AND ((:dataInicio BETWEEN curso.dataInicio AND curso.dataTermino) OR (:dataTermino BETWEEN curso.dataInicio AND curso.dataTermino))")
    @Transactional(readOnly = true)
    List<Integer> findCursoComMesmoPeriodo(Integer cursoId, LocalDate dataInicio, LocalDate dataTermino);
}
