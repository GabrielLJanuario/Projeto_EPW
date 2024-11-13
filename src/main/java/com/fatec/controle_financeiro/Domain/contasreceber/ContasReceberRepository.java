package com.fatec.controle_financeiro.domain.contasreceber;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fatec.controle_financeiro.entities.ContasReceber;

public interface ContasReceberRepository extends JpaRepository<ContasReceber, Long>{
    
}
