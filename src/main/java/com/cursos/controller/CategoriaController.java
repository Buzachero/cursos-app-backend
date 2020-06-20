package com.cursos.controller;

import com.cursos.model.Categoria;
import com.cursos.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping
    @CrossOrigin
    public ResponseEntity<List<Categoria>> getCategorias() {
        List<Categoria> categoriaList = categoriaService.getAllCategorias();

        return ResponseEntity.ok(categoriaList);
    }


}
