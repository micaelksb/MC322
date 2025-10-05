package com.rpg.combate;

import com.rpg.personagens.herois.Atreus;
import com.rpg.utils.Util;
import com.rpg.exceptions.RecursoInsuficienteException;

// habilidade especial do atreus
public class FlechaDeRaio implements AcaoDeCombate {

    private static final int CUSTO_MANA = 30;

    @Override
    public void executar(Combatente usuario, Combatente alvo) throws RecursoInsuficienteException {
        if (usuario instanceof Atreus) {
            Atreus atreus = (Atreus) usuario;

            if (atreus.getMana() < CUSTO_MANA) {
                throw new RecursoInsuficienteException(atreus.getNome() + " não tem mana suficiente para usar Flecha de Raio!");
            } else {
                int danoMagico = atreus.getNivel() * 5;

                if (Util.gerarNumeroAleatorio() < atreus.getSorte()) {
                    danoMagico *= 2; // dano crítico
                    System.out.println(atreus.getNome() + " teve sorte! Flecha de Raio crítica em " + alvo.getNome() + ", causando " + danoMagico + " de dano!");
                } else {
                    System.out.println(atreus.getNome() + " lança uma Flecha de Raio em " + alvo.getNome() + ", causando " + danoMagico + " de dano.");
                }

                alvo.receberDano(danoMagico);
                atreus.setMana(atreus.getMana() - CUSTO_MANA);
            }
        }
    }
}

