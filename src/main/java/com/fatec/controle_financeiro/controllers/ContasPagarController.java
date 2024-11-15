package com.fatec.controle_financeiro.controllers;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fatec.controle_financeiro.Domain.Fornecedor.FornecedorRepository;
import com.fatec.controle_financeiro.Domain.contaspagar.ContasPagarRepository;
import com.fatec.controle_financeiro.entities.ContasPagar;
import com.fatec.controle_financeiro.entities.Fornecedor;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;




@RequestMapping("/api/ContasPagar")
@RestController

public class ContasPagarController {

    Fornecedor fornecedor = new Fornecedor();

    private String msgErroFornecedor = "Fornecedor não encontrado";
    private String msgErroValor = "Valor da conta não pode ser menor que 0";
    private String msgErroData = "Data de emissão é posterior a de vencimento";

    @Autowired
    private ContasPagarRepository contasPagarRepository;
    @Autowired
    private FornecedorRepository fornecedorRepository;

    @PostMapping("/CREATE")
    public ResponseEntity<?> createContaPagar(@RequestBody ContasPagar contaspagar) {

        if(contaspagar.getVencimento().isAfter(contaspagar.getEmissao())){
            if((contaspagar.getValor().compareTo(BigDecimal.ZERO) > 0)){
                if(fornecedorRepository.findById(contaspagar.getFornecedor().getId()).isPresent() && contaspagar.getFornecedor() != null ) {
                    //id e buscar no fornecedor se id existe
                    //nao existe -> mostrar msg
                        ContasPagar contaspagarCreated = contasPagarRepository.save(contaspagar);
                        return new ResponseEntity<>(contaspagarCreated, HttpStatus.CREATED);
                }else{
                    //Fornecedor nulo
                    return new ResponseEntity<>(msgErroFornecedor, HttpStatus.EXPECTATION_FAILED);
                }
            }else{
                //Valor menor que 0
                return new ResponseEntity<>(msgErroValor, HttpStatus.EXPECTATION_FAILED);
            }
        }else{
            //Data emissao inválida
            return new ResponseEntity<>(msgErroData, HttpStatus.EXPECTATION_FAILED);
        }
    }
    
    @GetMapping("/READ")
    public ResponseEntity<List<ContasPagar>> getAllContasPagar() {
        List<ContasPagar> contaspagar = contasPagarRepository.findAll();
        return new ResponseEntity<>(contaspagar, HttpStatus.OK);
    }
    
    @GetMapping("/READ/{id}")
    public ResponseEntity<ContasPagar> getByIdContasPagar(@PathVariable long id) {
        Optional<ContasPagar> contaspagar = contasPagarRepository.findById(id);
        if(contaspagar.isPresent()){
            return new ResponseEntity<>(contaspagar.get(), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @PutMapping("/UPDATE/{id}")
    public ResponseEntity<?> updateContaPagar(@PathVariable long id, @RequestBody ContasPagar entityContasPagar) {
        //TODO: process PUT request
        Optional<ContasPagar> contaAtual = contasPagarRepository.findById(id);
        if(contaAtual.isPresent()){
            if(entityContasPagar.getVencimento().isAfter(entityContasPagar.getEmissao())){
                if((entityContasPagar.getValor().compareTo(BigDecimal.ZERO) > 0)){
                    if(fornecedorRepository.findById(entityContasPagar.getFornecedor().getId()).isPresent() && entityContasPagar.getFornecedor() != null ){
                        ContasPagar contaspagarCreated = contasPagarRepository.save(entityContasPagar);
                        return new ResponseEntity<>(contaspagarCreated, HttpStatus.OK);
                    }else{
                        //Fornecedor nulo
                        return new ResponseEntity<>(msgErroFornecedor, HttpStatus.EXPECTATION_FAILED);
                    }
                }else{
                    //Valor menor que 0
                    return new ResponseEntity<>(msgErroValor, HttpStatus.EXPECTATION_FAILED);
                }
            }else{
                //Data emissao inválida
                return new ResponseEntity<>(msgErroData, HttpStatus.EXPECTATION_FAILED);
            }
            
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/DELETE/{id}")
    public ResponseEntity<Void> deleteContaPagar(@PathVariable long id){
        Optional<ContasPagar> contaAtual = contasPagarRepository.findById(id);
        if(contaAtual.isPresent()){
            contasPagarRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }



}
