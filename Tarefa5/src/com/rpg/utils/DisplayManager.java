package com.rpg.utils;

import com.rpg.personagens.Heroi;
import com.rpg.personagens.Monstro;

/**
 * Classe utilitária para gerenciar a exibição de informações no console.
 */
public class DisplayManager {

    /**
     * Exibe informações detalhadas sobre as classes de heróis disponíveis.
     */
    public static void exibirInformacoesHerois() {
        System.out.println("\n========================================");
        System.out.println("        INFORMAÇÕES DOS HERÓIS        ");
        System.out.println("========================================");
        System.out.println("\n--- Guerreiro (Kratos) ---");
        System.out.println("Um combatente robusto, mestre em combate corpo a corpo.");
        System.out.println("Especialidade: Fúria Espartana (dano massivo, consome fúria).");
        System.out.println("Atributos base: Alta vida e força, boa defesa.");
        System.out.println("Recurso: Fúria (acumulada em combate).");
        System.out.println("\n--- Arqueiro (Atreus) ---");
        System.out.println("Um ágil atirador, especialista em ataques à distância.");
        System.out.println("Especialidade: Flecha de Raio (dano mágico, consome mana).");
        System.out.println("Atributos base: Média vida, média força, alta sorte.");
        System.out.println("Recurso: Mana (regenera lentamente).");
    }

    /**
     * Exibe informações sobre os tipos de monstros que podem ser encontrados.
     */
    public static void exibirInformacoesMonstros() {
        System.out.println("\n========================================");
        System.out.println("        INFORMAÇÕES DOS MONSTROS        ");
        System.out.println("========================================");
        System.out.println("\n--- Ciclope ---");
        System.out.println("Gigante de um olho só, conhecido por sua força bruta.");
        System.out.println("Habilidades: Ataques físicos poderosos.");
        System.out.println("Pontos fracos: Lento, fácil de prever.");
        System.out.println("\n--- Esfinge ---");
        System.out.println("Criatura mística com corpo de leão e cabeça humana, adora enigmas.");
        System.out.println("Habilidades: Ataques mentais, exige inteligência para ser derrotada.");
        System.out.println("Pontos fracos: Frágil em combate direto.");
        System.out.println("\n--- Cerbero ---");
        System.out.println("Cão de três cabeças guardião do submundo, extremamente agressivo.");
        System.out.println("Habilidades: Ataques rápidos e múltiplos, alta resistência.");
        System.out.println("Pontos fracos: Pode ser distraído por iscas.");
    }

    /**
     * Exibe o status completo de um herói.
     * @param heroi O herói cujo status será exibido.
     */
    public static void exibirStatusPersonagem(Heroi heroi) {
        System.out.println("\n--- STATUS DO HERÓI ---");
        heroi.exibirStatus();
    }
    
    /**
     * Exibe o status completo de um monstro.
     * @param monstro O monstro cujo status será exibido.
     */
    public static void exibirStatusMonstro(Monstro monstro) {
        System.out.println("\n--- STATUS DO MONSTRO ---");
        monstro.exibirStatus();
    }
}