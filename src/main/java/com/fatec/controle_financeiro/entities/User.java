package com.fatec.controle_financeiro.entities;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class User {
    private int id;
    
        @NotBlank(message = "O nome não pode estar em branco.")
        private String name;

        @NotNull(message = "A idade é obrigatória.")
        @Min(value = 0, message = "A idade não pode ser negativa.")
        private int age;

        // Construtor padrão
        public User() {
        }

        public User(int id, String name, int age){
            this.id = id;
            this.name = name;
            this.age = age;
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

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

    }