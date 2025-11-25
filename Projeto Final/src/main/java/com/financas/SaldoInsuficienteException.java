package com.financas;

/**
 * Exceção lançada quando uma operação requer mais saldo do que o disponível.
 * Esta é uma das duas exceções personalizadas obrigatórias do projeto.
 */
public class SaldoInsuficienteException extends Exception {
    
    public SaldoInsuficienteException(String mensagem) {
        super(mensagem);
    }
    
    public SaldoInsuficienteException(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }
}
