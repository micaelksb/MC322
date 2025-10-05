package com.rpg.cenario;

import java.util.List;
import com.rpg.core.Dificuldade;

/**
 * Interface para geradores de fases do jogo.
 * Permite a criação de diferentes estratégias de geração de cenários 
 */
public interface GeradorDeFases {

    /**
     * Gera uma lista de fases para o jogo com base na quantidade e dificuldade desejadas.
     *
     * @param quantidadeDeFases O número de fases a serem geradas.
     * @param dificuldade A dificuldade do jogo, que influenciará os monstros e recompensas.
     * @return Uma lista de objetos {@link Fase} que compõem a aventura.
     */
    List<Fase> gerar(int quantidadeDeFases, com.rpg.core.Dificuldade dificuldade);
}