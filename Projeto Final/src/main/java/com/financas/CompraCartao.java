package com.financas;

import java.time.LocalDate;

/**
 * Representa uma compra realizada com cartão de crédito.
 * Estende a classe Despesa e adiciona informações específicas do cartão.
 * Demonstra polimorfismo: é uma Despesa, mas com comportamento específico.
 */
public class CompraCartao extends Despesa {
    
    private static final long serialVersionUID = 1L;
    
    private int idCartao;
    private boolean faturaPaga;
    
    /**
     * Construtor da classe CompraCartao.
     * 
     * @param valor Valor da compra
     * @param data Data da compra
     * @param descricao Descrição da compra
     * @param categoria Categoria da compra
     * @param idCartao ID do cartão utilizado
     */
    public CompraCartao(double valor, LocalDate data, String descricao, 
                       Categoria categoria, int idCartao) {
        super(valor, data, descricao, categoria);
        this.idCartao = idCartao;
        this.faturaPaga = false;
    }
    
    public int getIdCartao() {
        return idCartao;
    }
    
    public void setIdCartao(int idCartao) {
        this.idCartao = idCartao;
    }
    
    public boolean isFaturaPaga() {
        return faturaPaga;
    }
    
    public void setFaturaPaga(boolean faturaPaga) {
        this.faturaPaga = faturaPaga;
    }
    
    @Override
    public String getTipo() {
        return "COMPRA CARTÃO";
    }
    
    @Override
    public String toString() {
        return String.format("[%d] %s - %s: R$ %.2f em %s (Cartão ID: %d, Paga: %s)",
                id, getTipo(), descricao, valor, data, idCartao, faturaPaga ? "Sim" : "Não");
    }
}
