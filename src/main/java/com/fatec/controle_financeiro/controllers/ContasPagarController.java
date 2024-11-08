package com.fatec.controle_financeiro.controllers;

import com.fatec.controle_financeiro.entities.ContasPagar;
import com.fatec.controle_financeiro.entities.Fornecedor;
import com.fatec.controle_financeiro.repositories.ContasPagarRepository;
import com.fatec.controle_financeiro.repositories.FornecedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/contas-pagar")
public class ContasPagarController {

    @Autowired
    private ContasPagarRepository contasPagarRepository;

    @Autowired
    private FornecedorRepository fornecedorRepository;

    // CREATE
    @PostMapping("/CREATE")
    public ResponseEntity<ContasPagar> create(@RequestBody ContasPagar contasPagar) {
        // Verificação: data de emissão não pode ser posterior à data de vencimento
        if (contasPagar.getEmissao().isAfter(contasPagar.getVencimento())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);  // Data inválida
        }

        // Verificação: valor da conta não pode ser negativo
        if (contasPagar.getValor().compareTo(BigDecimal.ZERO) <= 0) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);  // Valor inválido
        }

        // Verificação: fornecedor deve existir
        Optional<Fornecedor> fornecedor = fornecedorRepository.findById(contasPagar.getFornecedor().getId());
        if (!fornecedor.isPresent()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);  // Fornecedor não encontrado
        }

        // Salvar a conta a pagar
        contasPagar.setFornecedor(fornecedor.get());
        ContasPagar savedContasPagar = contasPagarRepository.save(contasPagar);
        return new ResponseEntity<>(savedContasPagar, HttpStatus.CREATED);
    }

    // READ - Obter todas as contas a pagar
    @GetMapping("/Listar_CP_Tudo")
    public ResponseEntity<List<ContasPagar>> getAll() {
        List<ContasPagar> contasPagarList = contasPagarRepository.findAll();
        return new ResponseEntity<>(contasPagarList, HttpStatus.OK);
    }

    // READ - Obter conta a pagar por ID
    @GetMapping("/READER/{id}")
    public ResponseEntity<ContasPagar> getById(@PathVariable Long id) {
        Optional<ContasPagar> contasPagar = contasPagarRepository.findById(id);
        return contasPagar.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                          .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // UPDATE
    @PutMapping("/UPDATE/{id}")
    public ResponseEntity<ContasPagar> update(@PathVariable Long id, @RequestBody ContasPagar contasPagar) {
        Optional<ContasPagar> existingContasPagar = contasPagarRepository.findById(id);
        if (!existingContasPagar.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);  // Conta não encontrada
        }

        // Verificação: data de emissão não pode ser posterior à data de vencimento
        if (contasPagar.getEmissao().isAfter(contasPagar.getVencimento())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);  // Data inválida
        }

        // Verificação: valor da conta não pode ser negativo
        if (contasPagar.getValor().compareTo(BigDecimal.ZERO) <= 0) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);  // Valor inválido
        }

        // Atualizar conta a pagar
        ContasPagar updatedContasPagar = existingContasPagar.get();
        updatedContasPagar.setEmissao(contasPagar.getEmissao());
        updatedContasPagar.setVencimento(contasPagar.getVencimento());
        updatedContasPagar.setValor(contasPagar.getValor());

        Optional<Fornecedor> fornecedor = fornecedorRepository.findById(contasPagar.getFornecedor().getId());
        if (!fornecedor.isPresent()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);  // Fornecedor não encontrado
        }

        updatedContasPagar.setFornecedor(fornecedor.get());
        contasPagarRepository.save(updatedContasPagar);
        return new ResponseEntity<>(updatedContasPagar, HttpStatus.OK);
    }

    // DELETE
    @DeleteMapping("/DELETE/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        Optional<ContasPagar> contasPagar = contasPagarRepository.findById(id);
        if (contasPagar.isPresent()) {
            contasPagarRepository.delete(contasPagar.get());
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);  // Conta deletada com sucesso
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);  // Conta não encontrada
    }
}
