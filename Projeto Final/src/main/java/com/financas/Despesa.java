package com.financas;

import java.time.LocalDate;

/**
 * Representa uma transação de despesa (saída de dinheiro).
 * Estende a classe abstrata Transacao.
 */
public class Despesa extends Transacao {
    
    private static final long serialVersionUID = 1L;
    
    /**
     * Construtor da classe Despesa.
     * 
     * @param valor Valor da despesa
     * @param data Data da despesa
     * @param descricao Descrição da despesa
     * @param categoria Categoria da despesa
     */
    public Despesa(double valor, LocalDate data, String descricao, Categoria categoria) {
        super(valor, data, descricao, categoria);
    }
    
    @Override
    public String getTipo() {
        return "DESPESA";
    }
}
