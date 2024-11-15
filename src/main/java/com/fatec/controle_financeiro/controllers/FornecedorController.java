package com.fatec.controle_financeiro.controllers;

/* import java.util.ArrayList; */
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

import com.fatec.controle_financeiro.Domain.Fornecedor.FornecedorRepository;
import com.fatec.controle_financeiro.entities.Fornecedor;

@RequestMapping("/api/Fornecedor")
@RestController

public class FornecedorController {

    @Autowired
    private FornecedorRepository fornecedorRepository;
    
/*     private List<Fornecedor> fornecedores = new ArrayList<>();
    private int proximoId = 1; */

    @PostMapping("/CREATE")
    public ResponseEntity<Fornecedor> createFornecedor(@RequestBody Fornecedor fornecedor){

        /* fornecedor.setId(proximoId++);
        fornecedores.add(fornecedor);

        return new ResponseEntity<>(fornecedor, HttpStatus.CREATED); */

        Fornecedor fornecedorCreated = fornecedorRepository.save(fornecedor);
        return new ResponseEntity<>(fornecedorCreated, HttpStatus.CREATED);
    }

    @GetMapping("/READ")
    public ResponseEntity<List<Fornecedor>> getAllFornecedor(){
           /*  return new ResponseEntity<>(fornecedores, HttpStatus.OK); */
           List<Fornecedor> fornecedores =fornecedorRepository.findAll();
           return new ResponseEntity<>(fornecedores, HttpStatus.OK);
    }

    @GetMapping("/READ/{id}")
    public ResponseEntity<Fornecedor> getByIdFornecedor(@PathVariable int id){
        /* for(Fornecedor countFornecedor : fornecedores){
            if(countFornecedor.getId() == id){
                return new ResponseEntity<>(countFornecedor, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND); */
        Optional<Fornecedor> fornecedor = fornecedorRepository.findById(id);
        if(fornecedor.isPresent()){
            return new ResponseEntity<>(fornecedor.get(), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/UPDATE/{id}")
    public ResponseEntity<Fornecedor> updateFornecedor(@PathVariable int id, @RequestBody Fornecedor entityFornecedor){
        /* for(Fornecedor fornecedor : fornecedores){
            if(entityFornecedor.getId() == id){
                fornecedor.setId(entityFornecedor.getId());
                fornecedor.setNome(entityFornecedor.getNome());
                return new ResponseEntity<>(fornecedor, HttpStatus.OK);
            }
        }

        return new ResponseEntity<>(entityFornecedor, HttpStatus.OK); */

        Optional<Fornecedor> fornecedorAtual = fornecedorRepository.findById(id);
        if(fornecedorAtual.isPresent()){
            entityFornecedor.setId(id);
            fornecedorRepository.save(entityFornecedor);
            return new ResponseEntity<>(entityFornecedor, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/DELETE/{id}")
    public ResponseEntity<Void> deleteFornecedor(@PathVariable int id){
        /* for(Fornecedor fornecedor : fornecedores){
            if(fornecedor.getId() == id){
                fornecedores.remove(fornecedor);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT); */
    
        Optional<Fornecedor> fornecedorAtual = fornecedorRepository.findById(id);
        if(fornecedorAtual.isPresent()){
            fornecedorRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //CREATE, READ, UPDATE E DELETE
}
