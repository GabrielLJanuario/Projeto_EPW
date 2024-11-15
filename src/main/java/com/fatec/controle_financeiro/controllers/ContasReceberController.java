package com.fatec.controle_financeiro.controllers;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fatec.controle_financeiro.Domain.Cliente.ClienteRepository;
import com.fatec.controle_financeiro.Domain.contasreceber.ContasReceberRepository;
import com.fatec.controle_financeiro.entities.ContasReceber;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;




@RequestMapping("/api/ContasReceber")
@RestController

public class ContasReceberController {
    
    private String msgErroCliente = "Cliente não encontrado";
    private String msgErroValor = "Valor da conta não pode ser menor que 0";
    private String msgErroData = "Data de emissão é posterior a de vencimento";

    @Autowired
    private ContasReceberRepository contasReceberRepository;
    @Autowired
    private ClienteRepository clienteRepository;

    @PostMapping("/CREATE")
    public ResponseEntity<?> createContaReceber(@RequestBody ContasReceber contasreceber) {
        //TODO: process POST request
        if(contasreceber.getVencimento().isAfter(contasreceber.getEmissao())){
            if((contasreceber.getValor().compareTo(BigDecimal.ZERO) > 0)){
                //Verificar por que está caindo null
                if(clienteRepository.findById(contasreceber.getCliente().getId()).isPresent() && contasreceber.getCliente() != null){
                    ContasReceber contasreceberCreated = contasReceberRepository.save(contasreceber);
                    return new ResponseEntity<>(contasreceberCreated, HttpStatus.CREATED);
                }else{
                    //Cliente nulo
                    return new ResponseEntity<>(msgErroCliente, HttpStatus.EXPECTATION_FAILED);
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
    public ResponseEntity<List<ContasReceber>> getAllContasReceber() {
        List<ContasReceber> contasreceber = contasReceberRepository.findAll();
        return new ResponseEntity<>(contasreceber, HttpStatus.OK);
    }
    
    @GetMapping("/READ/{id}")
    public ResponseEntity<ContasReceber> getByIdContasReceber(@PathVariable long id) {
        Optional<ContasReceber> contasreceber = contasReceberRepository.findById(id);
        if(contasreceber.isPresent()){
            return new ResponseEntity<>(contasreceber.get(), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @PutMapping("/UPDATE/{id}")
    public ResponseEntity<?> updateContaReceber(@PathVariable long id, @RequestBody ContasReceber entityContasReceber) {
        //TODO: process PUT request
        Optional<ContasReceber> contaAtual = contasReceberRepository.findById(id);
        if(contaAtual.isPresent()){
            if(entityContasReceber.getVencimento().isAfter(entityContasReceber.getEmissao())){
                if((entityContasReceber.getValor().compareTo(BigDecimal.ZERO) > 0)){
                    //Verificar por que está caindo null
                    if(clienteRepository.findById(entityContasReceber.getCliente().getId()).isPresent() && entityContasReceber.getCliente() != null){
                        entityContasReceber.setId(id);    
                        ContasReceber contasreceberCreated = contasReceberRepository.save(entityContasReceber);
                        return new ResponseEntity<>(contasreceberCreated, HttpStatus.OK);
                    }else{
                        //Cliente nulo
                        return new ResponseEntity<>(msgErroCliente, HttpStatus.EXPECTATION_FAILED);
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
    public ResponseEntity<Void> deleteContaReceber(@PathVariable long id){
        Optional<ContasReceber> contaAtual = contasReceberRepository.findById(id);
        if(contaAtual.isPresent()){
            contasReceberRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
