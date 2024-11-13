package com.fatec.controle_financeiro.entities;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "contas_receber")
public class ContasReceber {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
 
    @Column(name = "emissao")
    private LocalDate emissao;
 
    @Column(name = "vencimento")
    private LocalDate vencimento;
 
    @ManyToOne
    @JoinColumn(name = "cliente")
    private Cliente cliente;
 
    @Column(name = "valor", precision = 12, scale = 2)
    private BigDecimal valor;

    public void ContasReceber() {}
 
    public Long getId() {
        return id;
    }
 
    public void setId(Long id) {
        this.id = id;
    }
 
    public LocalDate getEmissao() {
        return emissao;
    }
 
    public void setEmissao(LocalDate emissao) {
        this.emissao = emissao;
    }
 
    public LocalDate getVencimento() {
        return vencimento;
    }
 
    public void setVencimento(LocalDate vencimento) {
        this.vencimento = vencimento;
    }
 
    public Cliente getCliente() {
        return cliente;
    }
 
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
 
    public BigDecimal getValor() {
        return valor;
    }
 
    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

}
