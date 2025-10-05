package com.rpg.combate;

/**
 * Representa qualquer entidade que pode participar de uma batalha.
 * Define os comportamentos essenciais para o combate, como atacar e receber dano.
 */
public interface Combatente {

    /**
     * Retorna o nome do combatente.
     * @return O nome do combatente.
     */
    String getNome();

    /**
     * Verifica se o combatente ainda está vivo.
     * @return {@code true} se os pontos de vida forem maiores que 0, {@code false} caso contrário.
     */
    boolean estaVivo();

    /**
     * Reduz os pontos de vida do combatente com base no dano sofrido.
     * @param dano A quantidade de dano a ser subtraída dos pontos de vida.
     */
    void receberDano(int dano);

    /**
     * Aumenta os pontos de vida do combatente.
     * @param cura A quantidade de vida a ser restaurada.
     */
    void receberCura(int cura);

    /**
     * Define a lógica para a escolha da próxima ação do combatente em seu turno.
     * @param alvo O combatente que será o alvo da ação escolhida.
     * @return A instância de {@link AcaoDeCombate} que será executada.
     */
    AcaoDeCombate escolherAcao(Combatente alvo);
}