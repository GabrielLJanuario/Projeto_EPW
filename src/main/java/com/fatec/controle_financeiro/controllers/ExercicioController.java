package com.fatec.controle_financeiro.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/exercicios1")
public class ExercicioController {
    
    @GetMapping()
    public String HelloWorld(){
        return "Hello";
    }

    @GetMapping("/reverter-nome/{nome}")
    String reverterNome(@PathVariable String nome){
        return new StringBuilder(nome).reverse().toString();
    }

    @PostMapping("/reverter-nome/")
    String reverterNomeRequisicaoCorpo(@RequestBody String nome){
        return new StringBuilder(nome).reverse().toString();
    }
    
    @GetMapping("/contar-letras/{texto}")
    public String contaLetras(@PathVariable String texto){
        int tamanho = texto.length();
        return String.valueOf(tamanho);
    }

    @PostMapping("/contar-letras/")
    String contaLetrasRequisicaoCorpo(@RequestBody String texto){
        int tamanho = texto.length();
        return String.valueOf(tamanho);
    }

    @GetMapping("/idade-com-parametro-tipo-integer/{paramIdade}")
    public String faixa_etaria(@PathVariable Integer paramIdade){
        int idade = paramIdade;
        /*if(idade <0){
            int idade = paramIdade;
            throw new NumberFormatException();
        }*/
        if(idade < 0 || idade > 120){
            return "Idade invalida";}
            else{
                if (idade < 12) {
                    return "Crianca";
                }
                else if(idade <= 18){
                    return "Adolescente";
                }
                else if(idade <= 60){
                    return "Adulto";
                }
                else{
                    return "Idoso";
                }
            }
        /*catch (NumberFormatException e){
            //Se a conversão falhar, significa que a string não é um número válido
            return "idade invalida";
        } */
        }
}
