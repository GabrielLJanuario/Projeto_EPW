package com.fatec.controle_financeiro.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "fornecedores")

public class Fornecedor{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int id;

    @Column(nullable = false, length = 60)

    private String nome;

    public Fornecedor(){
        
    }

    public Fornecedor(int id, String nome){
        this.id = id;
        this.nome = nome;
    }
    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
}