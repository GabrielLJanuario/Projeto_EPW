package com.fatec.controle_financeiro.domain.cliente;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fatec.controle_financeiro.entities.Cliente;

public interface ClienteRepository  extends JpaRepository<Cliente, Integer> {
}