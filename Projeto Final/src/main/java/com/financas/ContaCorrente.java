package com.financas;

/**
 * Representa uma conta corrente.
 * Estende a classe abstrata Conta.
 */
public class ContaCorrente extends Conta {
    
    private static final long serialVersionUID = 1L;
    
    /**
     * Construtor da classe ContaCorrente.
     * 
     * @param nome Nome da conta corrente
     * @param saldoInicial Saldo inicial
     */
    public ContaCorrente(String nome, double saldoInicial) {
        super(nome, saldoInicial);
    }
    
    @Override
    public String getTipo() {
        return "Conta Corrente";
    }
}
