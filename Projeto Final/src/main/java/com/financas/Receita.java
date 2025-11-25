package com.financas;

import java.time.LocalDate;

/**
 * Representa uma transação de receita (entrada de dinheiro).
 * Estende a classe abstrata Transacao.
 */
public class Receita extends Transacao {
    
    private static final long serialVersionUID = 1L;
    
    /**
     * Construtor da classe Receita.
     * 
     * @param valor Valor da receita
     * @param data Data da receita
     * @param descricao Descrição da receita
     * @param categoria Categoria da receita
     */
    public Receita(double valor, LocalDate data, String descricao, Categoria categoria) {
        super(valor, data, descricao, categoria);
    }
    
    @Override
    public String getTipo() {
        return "RECEITA";
    }
}
