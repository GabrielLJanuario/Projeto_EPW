package com.fatec.controle_financeiro.Domain.Fornecedor;

import com.fatec.controle_financeiro.entities.Fornecedor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FornecedorRepository extends JpaRepository<Fornecedor, Integer> {
}
