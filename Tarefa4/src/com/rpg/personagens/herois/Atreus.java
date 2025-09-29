package com.rpg.personagens.herois;

import com.rpg.combate.AtaqueFisico;
import com.rpg.combate.FlechaDeRaio;
import com.rpg.personagens.Heroi;

public class Atreus extends Heroi {
    
    private int mana;

    public Atreus(String nome, int pontosDeVida, int forca, int nivel, int experiencia, int mana) {
        super(nome, pontosDeVida, forca, nivel, experiencia);
        this.mana = mana;
        
        // lista de ações com as habilidades de Atreus
        this.acoes.add(new AtaqueFisico());
        this.acoes.add(new FlechaDeRaio());
    }
    
    @Override
    public int calcularDanoBase() {
        // o foco dele é a habilidade
        return this.forca / 2;
    }

    @Override
    public void exibirStatus() {
        super.exibirStatus();
        System.out.println("Mana: " + this.mana);
    }
    
    // Getters e Setters para a Mana
    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }
}