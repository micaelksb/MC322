package com.rpg.cenario;
import com.rpg.personagens.Heroi;
import com.rpg.cenario.TipoCenario;



// representa uma area ou etapa do jogo.
public interface Fase {
    
    void iniciar(Heroi heroi);

    boolean isConcluida();

    TipoCenario getTipoDeCenario();
}