package com.fatec.controle_financeiro.entities;

public class Cliente {
    private int id;
    
    // Desabilitar quando quiser trabalhar com @Valid
    //@NotBlank(message = "O nome não pode estar em branco.")
    private String nome;

    // Construtor padrão
    public Cliente() {
    }

    public Cliente(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    // Getters e Setters
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
