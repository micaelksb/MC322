package com.financas;

import java.io.Serializable;

/**
 * Classe abstrata que representa uma conta financeira.
 * Define o comportamento comum para diferentes tipos de contas.
 * Esta é uma das duas classes abstratas obrigatórias do projeto.
 */
public abstract class Conta implements Serializable {
    
    private static final long serialVersionUID = 1L;
    private static int proximoId = 1;
    
    protected int id;
    protected String nome;
    protected double saldo;
    
    /**
     * Construtor da classe Conta.
     * 
     * @param nome Nome da conta
     * @param saldoInicial Saldo inicial da conta
     */
    public Conta(String nome, double saldoInicial) {
        this.id = proximoId++;
        this.nome = nome;
        this.saldo = saldoInicial;
    }
    
    public int getId() {
        return id;
    }
    
    public String getNome() {
        return nome;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public double getSaldo() {
        return saldo;
    }
    
    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }
    
    /**
     * Deposita um valor na conta.
     * 
     * @param valor Valor a depositar
     * @throws IllegalArgumentException Se o valor for negativo ou zero
     */
    public void depositar(double valor) throws IllegalArgumentException {
        if (valor <= 0) {
            throw new IllegalArgumentException("Valor de depósito deve ser maior que zero");
        }
        this.saldo += valor;
    }
    
    /**
     * Saca um valor da conta.
     * 
     * @param valor Valor a sacar
     * @throws SaldoInsuficienteException Se não houver saldo suficiente
     * @throws IllegalArgumentException Se o valor for negativo ou zero
     */
    public void sacar(double valor) throws SaldoInsuficienteException, IllegalArgumentException {
        if (valor <= 0) {
            throw new IllegalArgumentException("Valor de saque deve ser maior que zero");
        }
        if (valor > this.saldo) {
            throw new SaldoInsuficienteException(
                String.format("Saldo insuficiente. Saldo atual: R$ %.2f, Valor solicitado: R$ %.2f",
                    this.saldo, valor)
            );
        }
        this.saldo -= valor;
    }
    
    /**
     * Método abstrato para obter o tipo de conta.
     * 
     * @return String representando o tipo de conta
     */
    public abstract String getTipo();
    
    @Override
    public String toString() {
        return String.format("[%d] %s - %s: R$ %.2f", id, getTipo(), nome, saldo);
    }
}
