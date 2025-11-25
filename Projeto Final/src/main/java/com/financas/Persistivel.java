package com.financas;

/**
 * Interface que define o contrato para persistência de dados.
 * Qualquer classe que implemente esta interface deve ser capaz de salvar e carregar seus dados.
 * Esta é uma das três interfaces obrigatórias do projeto.
 */
public interface Persistivel {
    
    /**
     * Salva os dados em arquivo.
     */
    void salvar();
    
    /**
     * Carrega os dados a partir do arquivo.
     */
    void carregar();
}
