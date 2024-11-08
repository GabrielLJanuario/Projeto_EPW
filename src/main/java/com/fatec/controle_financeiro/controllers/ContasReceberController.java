package com.fatec.controle_financeiro.controllers;

import com.fatec.controle_financeiro.entities.Cliente;
import com.fatec.controle_financeiro.entities.ContasReceber;
import com.fatec.controle_financeiro.repositories.ClienteRepository;
import com.fatec.controle_financeiro.repositories.ContasReceberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/contas-receber")
public class ContasReceberController {

    @Autowired
    private ContasReceberRepository contasReceberRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    // CREATE
    @PostMapping("/CREATE")
    public ResponseEntity<ContasReceber> create(@RequestBody ContasReceber contasReceber) {
        // Verificação: data de emissão não pode ser posterior à data de vencimento
        if (contasReceber.getEmissao().isAfter(contasReceber.getVencimento())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);  // Data inválida
        }

        // Verificação: valor da conta não pode ser negativo
        if (contasReceber.getValor().compareTo(BigDecimal.ZERO) <= 0) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);  // Valor inválido
        }

        // Verificação: cliente deve existir
        Optional<Cliente> cliente = clienteRepository.findById(contasReceber.getCliente().getId());
        if (!cliente.isPresent()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);  // Cliente não encontrado
        }

        // Salvar a conta a receber
        contasReceber.setCliente(cliente.get());
        ContasReceber savedContasReceber = contasReceberRepository.save(contasReceber);
        return new ResponseEntity<>(savedContasReceber, HttpStatus.CREATED);
    }

    // READ - Obter todas as contas a receber
    @GetMapping("LIST")
    public ResponseEntity<List<ContasReceber>> getAll() {
        List<ContasReceber> contasReceberList = contasReceberRepository.findAll();
        return new ResponseEntity<>(contasReceberList, HttpStatus.OK);
    }

    // READ - Obter conta a receber por ID
    @GetMapping("/READER/{id}")
    public ResponseEntity<ContasReceber> getById(@PathVariable Long id) {
        Optional<ContasReceber> contasReceber = contasReceberRepository.findById(id);
        return contasReceber.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                            .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // UPDATE
    @PutMapping("UPDATE/{id}")
    public ResponseEntity<ContasReceber> update(@PathVariable Long id, @RequestBody ContasReceber contasReceber) {
        Optional<ContasReceber> existingContasReceber = contasReceberRepository.findById(id);
        if (!existingContasReceber.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);  // Conta não encontrada
        }

        // Verificação: data de emissão não pode ser posterior à data de vencimento
        if (contasReceber.getEmissao().isAfter(contasReceber.getVencimento())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);  // Data inválida
        }

        // Verificação: valor da conta não pode ser negativo
        if (contasReceber.getValor().compareTo(BigDecimal.ZERO) <= 0) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);  // Valor inválido
        }

        // Atualizar conta a receber
        ContasReceber updatedContasReceber = existingContasReceber.get();
        updatedContasReceber.setEmissao(contasReceber.getEmissao());
        updatedContasReceber.setVencimento(contasReceber.getVencimento());
        updatedContasReceber.setValor(contasReceber.getValor());

        Optional<Cliente> cliente = clienteRepository.findById(contasReceber.getCliente().getId());
        if (!cliente.isPresent()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);  // Cliente não encontrado
        }

        updatedContasReceber.setCliente(cliente.get());
        contasReceberRepository.save(updatedContasReceber);
        return new ResponseEntity<>(updatedContasReceber, HttpStatus.OK);
    }

    // DELETE
    @DeleteMapping("/DELETE/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        Optional<ContasReceber> contasReceber = contasReceberRepository.findById(id);
        if (contasReceber.isPresent()) {
            contasReceberRepository.delete(contasReceber.get());
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);  // Conta deletada com sucesso
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);  // Conta não encontrada
    }
}
