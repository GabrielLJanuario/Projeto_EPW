package com.fatec.controle_financeiro.Domain.Fornecedor;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fatec.controle_financeiro.entities.Fornecedor;

public interface FornecedorRepository  extends JpaRepository<Fornecedor, Integer> {
    
}