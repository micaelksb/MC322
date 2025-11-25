package com.financas;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * Classe abstrata que representa uma transação financeira.
 * Define o comportamento comum para receitas, despesas e compras no cartão.
 * Esta é uma das duas classes abstratas obrigatórias do projeto.
 */
public abstract class Transacao implements Serializable {
    
    private static final long serialVersionUID = 1L;
    private static int proximoId = 1;
    
    protected int id;
    protected double valor;
    protected LocalDate data;
    protected String descricao;
    protected Categoria categoria;
    
    /**
     * Construtor da classe Transacao.
     * 
     * @param valor Valor da transação
     * @param data Data da transação
     * @param descricao Descrição da transação
     * @param categoria Categoria da transação
     */
    public Transacao(double valor, LocalDate data, String descricao, Categoria categoria) {
        this.id = proximoId++;
        this.valor = valor;
        this.data = data;
        this.descricao = descricao;
        this.categoria = categoria;
    }
    
    public int getId() {
        return id;
    }
    
    public double getValor() {
        return valor;
    }
    
    public void setValor(double valor) {
        this.valor = valor;
    }
    
    public LocalDate getData() {
        return data;
    }
    
    public void setData(LocalDate data) {
        this.data = data;
    }
    
    public String getDescricao() {
        return descricao;
    }
    
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    public Categoria getCategoria() {
        return categoria;
    }
    
    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
    
    /**
     * Método abstrato para obter o tipo de transação.
     * 
     * @return String representando o tipo de transação
     */
    public abstract String getTipo();
    
    @Override
    public String toString() {
        return String.format("[%d] %s - %s: R$ %.2f em %s (Categoria: %s)",
                id, getTipo(), descricao, valor, data, categoria.getNome());
    }
}
