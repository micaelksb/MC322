package com.rpg.personagens.monstros;

import com.rpg.personagens.Monstro;

public class Ciclope extends Monstro {
    private int agilidade;

    public Ciclope(String nome, int pontosDeVida, int forca, int xpConcedido, int agilidade) {
        super(nome, pontosDeVida, forca, xpConcedido);
        this.agilidade = agilidade;
    }

    @Override
    public int calcularDanoBase() {
        return this.forca + (this.agilidade / 3);
    }

    @Override
    public void exibirStatus() {
        super.exibirStatus();
        System.out.println("Agilidade: " + this.agilidade);
    }
}