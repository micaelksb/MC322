package com.financas;

/**
 * Representa uma conta poupança.
 * Estende a classe abstrata Conta.
 */
public class Poupanca extends Conta {
    
    private static final long serialVersionUID = 1L;
    
    /**
     * Construtor da classe Poupanca.
     * 
     * @param nome Nome da conta poupança
     * @param saldoInicial Saldo inicial
     */
    public Poupanca(String nome, double saldoInicial) {
        super(nome, saldoInicial);
    }
    
    @Override
    public String getTipo() {
        return "Poupança";
    }
}
