package com.rpg.core;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import com.rpg.cenario.ConstrutorDeCenarioFixo;
import com.rpg.cenario.Fase;
import com.rpg.combate.FaseDeCombate;
import com.rpg.personagens.Monstro;
import java.util.List;

/**
 * Classe de teste para o sistema de Dificuldade.
 */
public class DificuldadeTest {

    /**
     * testa se os atributos dos monstros mudam de acordo com a dificuldade escolhida
     * compara um monstro gerado na dificuldade facil com um gerado na dificil
     */
    @Test
    public void testDiferencaDeAtributosEntreDificuldades() {
        ConstrutorDeCenarioFixo gerador = new ConstrutorDeCenarioFixo();
        int quantidadeDeFases = 1;

        // gera a primeira fase na dificuldade facil
        List<Fase> fasesFacil = gerador.gerar(quantidadeDeFases, Dificuldade.FACIL);
        FaseDeCombate faseFacil = (FaseDeCombate) fasesFacil.get(0);
        Monstro monstroFacil = faseFacil.getMonstros().get(0);

        // gera a primeira fase na dificuldade dificil
        List<Fase> fasesDificil = gerador.gerar(quantidadeDeFases, Dificuldade.DIFICIL);
        FaseDeCombate faseDificil = (FaseDeCombate) fasesDificil.get(0);
        Monstro monstroDificil = faseDificil.getMonstros().get(0);

        // compara os pontos de vida
        assertTrue(monstroDificil.getPontosDeVida() > monstroFacil.getPontosDeVida(),
                "Monstro em dificuldade DIFÍCIL deve ter mais vida que na FÁCIL.");

        // compara a força
        assertTrue(monstroDificil.getForca() > monstroFacil.getForca(),
                "Monstro em dificuldade DIFÍCIL deve ter mais força que na FÁCIL.");
    }
}