package com.rpg.core;

/**
 * Enum que define os níveis de dificuldade do jogo.
 * Cada nível possui multiplicadores que alteram dinamicamente os atributos dos monstros.
 */
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

    /**
     * Retorna o multiplicador de vida para esta dificuldade.
     * @return O valor pelo qual a vida base dos monstros será multiplicada.
     */
    public double getVidaMultiplier() {
        return vidaMultiplier;
    }

    /**
     * Retorna o multiplicador de força para esta dificuldade.
     * @return O valor pelo qual a força base dos monstros será multiplicada.
     */
    public double getForcaMultiplier() {
        return forcaMultiplier;
    }
}