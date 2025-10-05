package com.rpg.combate;

import com.rpg.exceptions.RecursoInsuficienteException;

/**
 * Representa uma ação que um combatente pode realizar durante uma batalha.
 */
public interface AcaoDeCombate {

    /**
     * Executa a lógica da ação de um combatente (usuário) contra outro (alvo).
     *
     * @param usuario O combatente que está executando a ação.
     * @param alvo O combatente que está recebendo o efeito da ação.
     * @throws RecursoInsuficienteException se o usuário não tiver os recursos
     * necessários (mana, fúria, etc.) para executar a ação.
     */
    void executar(Combatente usuario, Combatente alvo) throws RecursoInsuficienteException;

}