package com.cursos.service;

import com.cursos.model.Categoria;
import com.cursos.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public List<Categoria> getAllCategorias() {
        return categoriaRepository.findAll();
    }

    public boolean isValidCategoria(Integer categoriaId) {
        return categoriaRepository.existsById(categoriaId);
    }
}
