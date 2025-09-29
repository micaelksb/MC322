package com.rpg.cenario;

import java.util.List;
import com.rpg.core.Dificuldade;

public interface GeradorDeFases {

    // gera uma lista de fases para o jogo.
    List<Fase> gerar(int quantidadeDeFases, com.rpg.core.Dificuldade dificuldade);
}