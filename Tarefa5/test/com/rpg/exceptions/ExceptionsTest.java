package com.rpg.exceptions;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import com.rpg.personagens.herois.Guerreiro;
import com.rpg.personagens.monstros.Ciclope;
import com.rpg.combate.FuriaEspartana;
import com.rpg.itens.LaminasDoCaos;

/**
 * Classe de teste para as exceções customizadas
 */
public class ExceptionsTest {

    /**
     * testa se a exceção RecursoInsuficienteException é lançada quando um Guerreiro tenta usar Furia Espartana sem fúria suficiente
     */
    @Test
    public void testLancarRecursoInsuficienteException() {
        // Cria um Guerreiro com apenas 10 de fúria (habilidade custa 20)
        Guerreiro guerreiro = new Guerreiro("Kratos Sem Furia", 100, 20, 1, 0, 10);
        Ciclope alvo = new Ciclope("Alvo de Teste", 50, 10, 10, 5);
        FuriaEspartana habilidade = new FuriaEspartana();

        // Verifica se a exceção é lançada ao tentar executar a habilidade
        RecursoInsuficienteException exception = assertThrows(
            RecursoInsuficienteException.class,
            () -> habilidade.executar(guerreiro, alvo),
            "Deveria lançar RecursoInsuficienteException por falta de fúria"
        );

        // verifica se a mensagem da exceção está correta
        String expectedMessage = guerreiro.getNome() + " não tem fúria suficiente para usar Fúria Espartana!";
        assertEquals(expectedMessage, exception.getMessage());
    }

    /**
     * Testa se a exceção NivelInsuficienteException é lançada quando um Herói de nível baixo tenta equipar uma arma de nível alto.
     */
    @Test
    public void testLancarNivelInsuficienteException() {
        // Cria um Guerreiro de nível 1
        Guerreiro guerreiro = new Guerreiro("Kratos Nivel 1", 100, 20, 1, 0, 10);
        // Cria uma arma que exige nível 3
        LaminasDoCaos laminas = new LaminasDoCaos(); // requer nível 3

        // verifica se a exceção é lançada ao tentar equipar a arma
        NivelInsuficienteException exception = assertThrows(
            NivelInsuficienteException.class,
            () -> guerreiro.equiparArma(laminas),
            "deveria lançar NivelInsuficienteException por nível baixo."
        );
        
        // opcional: verifica a mensagem da exceção
        String expectedMessage = guerreiro.getNome() + " não tem nivel suficiente para usar " + laminas.getClass().getSimpleName() + " (requer nivel " + laminas.getMinNivel() + ")";
        assertEquals(expectedMessage, exception.getMessage());
    }
}