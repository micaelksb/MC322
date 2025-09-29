package com.rpg.personagens.monstros;

import com.rpg.personagens.Monstro;

public class Cerbero extends Monstro {
    private int defesaOignea;

    public Cerbero(String nome, int pontosDeVida, int forca, int xpConcedido, int defesaOignea) {
        super(nome, pontosDeVida, forca, xpConcedido);
        this.defesaOignea = defesaOignea;
    }

    @Override
    public int calcularDanoBase() {
        return this.forca + (this.defesaOignea / 5);
    }


    @Override
    public void exibirStatus() {
        super.exibirStatus();
        System.out.println("Defesa Ígnea: " + this.defesaOignea);
    }
}