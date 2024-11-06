package com.fatec.controle_financeiro.controllers;

import com.fatec.controle_financeiro.entities.Fornecedor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/fornecedores")
public class FornecedorController {
    
    private List<Fornecedor> fornecedores = new ArrayList<>();
    private int proximoId = 1;

    // CREATE    
    @PostMapping()
    public ResponseEntity<Fornecedor> create(@RequestBody Fornecedor fornecedor) {
        for (Fornecedor f : fornecedores) {
            if (f.getNome().equals(fornecedor.getNome())) {
                throw new IllegalArgumentException("Já existe um fornecedor com esse nome.");
            }
        }
        fornecedor.setId(proximoId++);
        fornecedores.add(fornecedor);
        return new ResponseEntity<>(fornecedor, HttpStatus.CREATED);
    }

    // READ
    @GetMapping()
    public ResponseEntity<List<Fornecedor>> getAllFornecedores() {
        return new ResponseEntity<>(fornecedores, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Fornecedor> getById(@PathVariable int id) {
        for (Fornecedor fornecedor : fornecedores) {
            if (fornecedor.getId() == id) {
                return new ResponseEntity<>(fornecedor, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // UPDATE
    @PutMapping("{id}")
    public ResponseEntity<Fornecedor> updateFornecedor(@PathVariable int id, @RequestBody Fornecedor entity) {
        for (Fornecedor fornecedor : fornecedores) {
            if (fornecedor.getId() == id) {
                fornecedor.setNome(entity.getNome()); // Corrigido
                return new ResponseEntity<>(fornecedor, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFornecedor(@PathVariable int id) {
        for (Fornecedor fornecedor : fornecedores) {
            if (fornecedor.getId() == id) {
                fornecedores.remove(fornecedor);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
