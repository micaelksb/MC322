package com.financas;

/**
 * Exceção lançada quando uma data fornecida é inválida ou não atende aos critérios esperados.
 * Esta é uma das duas exceções personalizadas obrigatórias do projeto.
 */
public class DataInvalidaException extends Exception {
    
    public DataInvalidaException(String mensagem) {
        super(mensagem);
    }
    
    public DataInvalidaException(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }
}
