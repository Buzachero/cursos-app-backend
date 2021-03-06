package com.cursos.controller;

import com.cursos.exception.*;
import com.cursos.model.Curso;
import com.cursos.service.CursoService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/cursos")
public class CursoController {

    Logger logger = LoggerFactory.getLogger(CursoController.class);

    @Autowired
    private CursoService cursoService;

    @GetMapping(value = "/{id}")
    @ApiOperation(value = "Recupera curso pelo id",
                    notes = "Recupera os dados de determinado curso pelo identificador"
                )
    public ResponseEntity<Curso> getCursoById(@PathVariable Integer id) throws CursoNotFoundException {
        Curso curso = cursoService.getCursoById(id);

        return ResponseEntity.ok(curso);
    }

    @GetMapping(value = "/curso")
    @ApiOperation(value = "Recupera curso pela descrição",
                    notes = "Recupera os dados de determinado curso pela descrição"
    )
    public ResponseEntity<Curso> getCursoByDescricao(@RequestParam String descricao) throws CursoNotFoundException {
        Curso curso = cursoService.getCursoByDescricao(descricao);

        return ResponseEntity.ok(curso);
    }

    @CrossOrigin
    @GetMapping
    @ApiOperation(value = "Recupera todos os cursos",
                    notes = "Recupera os dados de todos os cursos"
    )
    public ResponseEntity<List<Curso>> getAllCursos() throws CursoNotFoundException {
        logger.debug("BUSCANDO TODOS OS CURSOS DA BASE ...");
        List<Curso> cursoList = cursoService.getAllCursos();

        return ResponseEntity.ok(cursoList);
    }

    @CrossOrigin
    @PostMapping
    @ApiOperation(value = "Adiciona um curso",
                    notes = "Adiciona um novo curso"
    )
    public ResponseEntity<Curso> addCurso(@Valid @RequestBody Curso newCurso) throws CursoApplicationException {
        logger.debug("CURSO A SER ADICIONADO:\n" + newCurso);
        Curso curso = cursoService.addCurso(newCurso);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(curso.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }

    @CrossOrigin
    @PutMapping(value="/{id}")
    @ApiOperation(value = "Atualiza um curso",
                    notes = "Atualiza um curso existente"
    )
    public ResponseEntity<Void> update(@PathVariable("id") Integer id,
                                        @RequestBody Curso cursoAtualizado) throws CursoApplicationException {
        cursoService.updateCurso(id, cursoAtualizado);

        return ResponseEntity.ok().build();
    }

    @CrossOrigin
    @DeleteMapping(value = "/{id}")
    @ApiOperation(value = "Remove um curso",
            notes = "Remove um curso existente"
    )
    public ResponseEntity<Void> delete(@PathVariable Integer id) throws CursoNotFoundException {
        cursoService.deleteCurso(id);

        return ResponseEntity.noContent().build();
    }

}
