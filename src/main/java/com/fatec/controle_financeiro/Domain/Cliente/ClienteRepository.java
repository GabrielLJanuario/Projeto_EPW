package com.fatec.controle_financeiro.Domain.Cliente;

import com.fatec.controle_financeiro.entities.ContasPagar;
import org.springframework.data.jpa.repository.JpaRepository;
public interface ClienteRepository extends JpaRepository<ContasPagar, Long> {
}
