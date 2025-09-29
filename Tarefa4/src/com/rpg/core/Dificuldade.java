package com.rpg.core;

public enum Dificuldade {
    FACIL(0.8, 0.8), // Monstros com 80% da vida e força base
    NORMAL(1.0, 1.0), // Monstros com 100% da vida e força base
    DIFICIL(1.2, 1.2); // Monstros com 120% da vida e força base

    private final double vidaMultiplier;
    private final double forcaMultiplier;

    Dificuldade(double vidaMultiplier, double forcaMultiplier) {
        this.vidaMultiplier = vidaMultiplier;
        this.forcaMultiplier = forcaMultiplier;
    }

    public double getVidaMultiplier() {
        return vidaMultiplier;
    }

    public double getForcaMultiplier() {
        return forcaMultiplier;
    }
}

