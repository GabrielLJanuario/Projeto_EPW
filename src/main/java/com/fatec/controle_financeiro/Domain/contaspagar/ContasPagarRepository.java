package com.fatec.controle_financeiro.Domain.contaspagar;

import com.fatec.controle_financeiro.entities.ContasPagar;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContasPagarRepository extends JpaRepository<ContasPagar, Long> {
}
