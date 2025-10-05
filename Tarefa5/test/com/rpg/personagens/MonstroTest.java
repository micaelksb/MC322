package com.rpg.personagens;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.rpg.personagens.herois.Guerreiro;
import com.rpg.personagens.monstros.Ciclope;
import com.rpg.combate.AcaoDeCombate;
import com.rpg.combate.Combatente;
import com.rpg.itens.Item;
import com.rpg.itens.Lootavel;

/**
 * Classe de teste para a classe Monstro e suas subclasses
 */
public class MonstroTest {

    private Monstro monstro;
    private Heroi alvo;

    /**
     * Configura um novo monstro e um herói alvo antes de cada teste
     */
    @BeforeEach
    public void setUp() {
        monstro = new Ciclope("Ciclope de Teste", 80, 15, 25, 8);
        alvo = new Guerreiro("Kratos Alvo", 100, 20, 1, 0, 10);
    }

    /**
     * Testa se a classe do monstro implementa corretamente a interface combatente
     */
    @Test
    public void testImplementaCombatente() {
        assertTrue(monstro instanceof Combatente, "Monstro deve implementar a interface Combatente.");
    }

    /**
     * Testa se a classe do Monstro implementa a interface Lootavel e se o método de dropar loot funciona
     */
    @Test
    public void testImplementaLootavel() {
        assertTrue(monstro instanceof Lootavel, "Monstro deve implementar a interface Lootavel.");
        // o método droparLoot pode retornar null, então apenas verificamos se a chamada não gera erro
        Item loot = ((Lootavel) monstro).droparLoot();
        // a lista de armas é inicializada no construtor de Monstro, então o loot não deve ser nulo.
        assertNotNull(loot, "O monstro deveria dropar um item.");
    }

    /**
     * Testa se o monstro recebe dano corretamente
     */
    @Test
    public void testReceberDano() {
        int vidaInicial = monstro.getPontosDeVida();
        int dano = 20;
        monstro.receberDano(dano);
        assertEquals(vidaInicial - dano, monstro.getPontosDeVida(), "Os pontos de vida do monstro devem ser reduzidos corretamente.");
    }

    /**
     * Testa se o monstro ataca um alvo, diminuindo a vida do alvo
     */
    @Test
    public void testAtacarAlvo() {
        int vidaInicialAlvo = alvo.getPontosDeVida();
        AcaoDeCombate ataque = monstro.escolherAcao(alvo);
        ataque.executar(monstro, alvo);
        assertTrue(alvo.getPontosDeVida() < vidaInicialAlvo, "O alvo deve ter menos vida após ser atacado pelo monstro.");
    }
}