package com.fatec.controle_financeiro.Domain.Categoria;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fatec.controle_financeiro.entities.Categoria;
import com.fatec.controle_financeiro.entities.Categoria;

public interface CategoriasRepository  extends JpaRepository<Categoria, Long> {

    boolean existsByDescricao(String descricao);
}