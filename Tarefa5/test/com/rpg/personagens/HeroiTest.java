package com.rpg.personagens;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.rpg.personagens.herois.Guerreiro;
import com.rpg.personagens.monstros.Ciclope;
import com.rpg.combate.Combatente;
import com.rpg.combate.AcaoDeCombate;

/**
 * Classe de teste para a classe Heroi e suas subclasses
 */
public class HeroiTest {

    private Guerreiro heroi;
    private Monstro alvo;

    /**
     * Configura um novo herói e um monstro alvo antes de cada teste
     */
    @BeforeEach
    public void setUp() {
        heroi = new Guerreiro("Kratos de Teste", 100, 20, 1, 0, 10);
        alvo = new Ciclope("Ciclope Alvo", 50, 10, 20, 5);
    }

    /**
     * Testa se a classe do herói implementa corretamente a interface combatente
     */
    @Test
    public void testImplementaCombatente() {
        assertTrue(heroi instanceof Combatente, "Herói deve implementar a interface Combatente.");
    }

    /**
     * testa se o herói recebe dano corretamente, diminuindo seus pontos de vida.
     */
    @Test
    public void testReceberDano() {
        int vidaInicial = heroi.getPontosDeVida();
        int dano = 15;
        heroi.receberDano(dano);
        assertEquals(vidaInicial - dano, heroi.getPontosDeVida(), "Os pontos de vida do herói devem ser reduzidos pela quantidade de dano.");
    }

    /**
     * Testa se o herói ataca um alvo, causando dano.
     * verifica se a vida do alvo diminui após o ataque.
     */
    @Test
    public void testAtacarAlvo() {
        int vidaInicialAlvo = alvo.getPontosDeVida();
        AcaoDeCombate ataque = heroi.escolherAcao(alvo); // Pega uma ação de ataque
        ataque.executar(heroi, alvo);
        assertTrue(alvo.getPontosDeVida() < vidaInicialAlvo, "O alvo deve ter menos vida após ser atacado pelo herói.");
    }
}