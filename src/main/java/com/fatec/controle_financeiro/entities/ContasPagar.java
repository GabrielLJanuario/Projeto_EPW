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
@Table(name = "contas_pagar")
public class ContasPagar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
 
    @Column(name = "emissao")
    private LocalDate emissao;
 
    @Column(name = "vencimento")
    private LocalDate vencimento;
 
    @ManyToOne
    @JoinColumn(name = "fornecedor")
    private Fornecedor fornecedor;
 
    @Column(name = "valor", precision = 12, scale = 2)
    private BigDecimal valor;
 
    public void ContasPagar() {}
 
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
 
    public Fornecedor getFornecedor() {
        return fornecedor;
    }
 
    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }
 
    public BigDecimal getValor() {
        return valor;
    }
 
    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

}