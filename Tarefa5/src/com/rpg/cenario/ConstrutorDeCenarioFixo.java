package com.rpg.cenario;

import com.rpg.combate.FaseDeCombate;
import com.rpg.personagens.Monstro;
import com.rpg.personagens.monstros.Cerbero;
import com.rpg.personagens.monstros.Ciclope;
import com.rpg.personagens.monstros.Esfinge;
import java.util.ArrayList;
import java.util.List;
import com.rpg.core.Dificuldade;
public class ConstrutorDeCenarioFixo implements GeradorDeFases {

    @Override
    public List<Fase> gerar(int quantidadeDeFases, com.rpg.core.Dificuldade dificuldade) {
        List<Fase> fases = new ArrayList<>();
        
        TipoCenario[] cenarios = {TipoCenario.MIDGARD, TipoCenario.ALFHEIM, TipoCenario.SVARTALFHEIM};
        
        for (int i = 1; i <= quantidadeDeFases; i++) {
            List<Monstro> monstrosDaFase = new ArrayList<>();
            
            int vidaBase = (int) ((30 + (i * 15)) * dificuldade.getVidaMultiplier());
            int forcaBase = (int) ((8 + (i * 3)) * dificuldade.getForcaMultiplier());
            int xpBase = 15 + (i * 5);
            
            monstrosDaFase.add(new Ciclope("Ciclope Lvl " + i, vidaBase, forcaBase, xpBase, 10 + i));
            monstrosDaFase.add(new Esfinge("Esfinge Lvl " + i, vidaBase + 10, forcaBase + 2, xpBase + 5, 8 + i));
            
            if (i >= 2) {
                monstrosDaFase.add(new Cerbero("Cerbero Lvl " + i, vidaBase + 20, forcaBase + 3, xpBase + 10, 12 + i));
            }
            // Lógica para dropar armas melhores em dificuldades maiores (exemplo simples)
            // Esta lógica pode ser expandida para ser mais sofisticada
            for (Monstro monstro : monstrosDaFase) {
                if (dificuldade == com.rpg.core.Dificuldade.DIFICIL) {
                    // Exemplo: Monstros em dificuldade DIFICIL podem ter uma chance maior de dropar itens específicos ou ter itens adicionais em sua lista de drops.
                }
            }
            
            TipoCenario cenario = (i <= cenarios.length) ? cenarios[i-1] : TipoCenario.MIDGARD;
            
            Fase fase = new FaseDeCombate(cenario, monstrosDaFase);
            fases.add(fase);
        }
        
        return fases;
    }
}