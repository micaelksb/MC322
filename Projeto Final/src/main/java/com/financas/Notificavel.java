package com.financas;

/**
 * Interface que define o contrato para envio de notificações.
 * Qualquer classe que implemente esta interface deve ser capaz de notificar o usuário.
 * Esta é uma das três interfaces obrigatórias do projeto.
 */
public interface Notificavel {
    
    /**
     * Envia uma notificação ao usuário.
     * 
     * @param mensagem A mensagem a ser notificada
     */
    void enviarNotificacao(String mensagem);
}
