package com.fatec.controle_financeiro.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


import org.springframework.data.jpa.repository.JpaRepository;
import com.fatec.controle_financeiro.entities.Cliente;

@Entity
@Table(name = "clientes")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 60)
    private String name;

    // Construtor padrão
    public Cliente() {

    }

    public Cliente(int id, String name) {

        this.id = id;

        this.name = name;

    }

    // Getters e Setters

    public int getId() { return id; }


    public void setId(int id) { this.id = id; }


    public String getName() {

        return name;

    }

    public void setName(String name) {

        this.name = name;

    }
}