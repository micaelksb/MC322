package com.rpg.itens;

// implementa a interface item
public abstract class Arma implements Item {
    protected int dano;
    protected int minNivel;
 
    public Arma(int dano, int minNivel) {
        this.dano = dano;
        this.minNivel = minNivel;
    }

    // implementa o método da interface item
    @Override
    public String getNome() {
        return this.getClass().getSimpleName();
    }

    public int getDano() {
        return dano;
    }

    public int getMinNivel() {
        return minNivel;
    }
}