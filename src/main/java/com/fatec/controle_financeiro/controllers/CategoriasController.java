package com.fatec.controle_financeiro.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fatec.controle_financeiro.Domain.Categoria.CategoriasRepository;
import com.fatec.controle_financeiro.entities.Categoria;

@RequestMapping("/api/Categorias")
@RestController
public class CategoriasController {

    @Autowired
    private CategoriasRepository categoriaRepository;

    @PostMapping("/CREATE")
    public ResponseEntity<?> createCategoria(@RequestBody Categoria categoria) {
        // Verificar se a descrição já está em uso
        if (categoriaRepository.existsByDescricao(categoria.getDescricao())) {
            return new ResponseEntity<>("Descrição já está em uso", HttpStatus.BAD_REQUEST);
        }

        // Se o campo 'ativo' não for enviado, será definido como 'true'
        if (categoria.getAtivo() == null) {
            categoria.setAtivo(true);
        }

        Categoria categoriaCreated = categoriaRepository.save(categoria);
        return new ResponseEntity<>(categoriaCreated, HttpStatus.CREATED);
    }

}
