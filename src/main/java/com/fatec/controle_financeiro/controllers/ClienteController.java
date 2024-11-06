package com.fatec.controle_financeiro.controllers;
import java.util.ArrayList;
import java.util.List;
//import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.fatec.controle_financeiro.entities.Cliente;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/api/clientes")
public class ClienteController {
    

    private List<Cliente> clientes = new ArrayList<>();
    private int proximoId = 1;

    // CREATE
    @PostMapping("/CREATE")
    public ResponseEntity<Cliente> createCliente(@RequestBody Cliente cliente) {

        for (Cliente c : clientes) {
            if (c.getNome().equals(cliente.getNome())) {
                throw new IllegalArgumentException("Já existe um cliente com esse nome.");
            }
        }

        cliente.setId(proximoId++);
        clientes.add(cliente);

        return new ResponseEntity<>(cliente, HttpStatus.CREATED);
    }

    // READ - Obter todos os clientes
    @GetMapping("/Listar_Cli_Tudo")
    public ResponseEntity<List<Cliente>> getAllClientes() {
        return new ResponseEntity<>(clientes, HttpStatus.OK);
    }

    // READ - Obter cliente por ID
    @GetMapping("/READER/{id}")
    public ResponseEntity<Cliente> getClienteById(@PathVariable int id) {
        for (Cliente cliente : clientes) {
            if (cliente.getId() == id) {
                return new ResponseEntity<>(cliente, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // UPDATE
    @PutMapping("/UPDATE/{id}")
    public ResponseEntity<Cliente> updateCliente(@PathVariable int id, @RequestBody Cliente clienteAtualizado) {
        for (Cliente cliente : clientes) {
            if (cliente.getId() == id) {
                cliente.setNome(clienteAtualizado.getNome());
                return new ResponseEntity<>(cliente, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // DELETE
    @DeleteMapping("/DELETE/{id}")
    public ResponseEntity<Void> deleteCliente(@PathVariable int id) {
        for (Cliente cliente : clientes) {
            if (cliente.getId() == id) {
                clientes.remove(cliente);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
