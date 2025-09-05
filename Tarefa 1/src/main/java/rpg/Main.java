package rpg;

import rpg.personagens.*;

public class Main {
    public static void main(String[] args) {
        // personagens
        Guerreiro heroi = new Guerreiro("Kratos", 100, 15, 1, 0, 10);
        Monstro[] monstros = {
            new Ciclope("Ciclope Pequeno", 30, 5, 10, 8),
            new Esfinge("Esfinge Voraz", 70, 12, 25, 5),
            new Cerbero("Cerbero de fogo", 40, 7, 15, 6)
        };

        // o desafio
        System.out.println("\n----------------------------------------------------");
        System.out.println("O HERÓI SOBE NO OLIMPA PARA ENFRENTAR SEUS DESAFIOS!");
        System.out.println("Prepare-se para a batalha, pois os deuses prepararam uma surpresa...");
        System.out.println("----------------------------------------------------\n");

        heroi.exibirStatus();

        for (int i = 0; i < monstros.length; i++) {
            Monstro monstroAtual = monstros[i];
            System.out.println("\n--- INÍCIO DO TURNO " + (i + 1) + " ---");
            System.out.println("Um " + monstroAtual.getNome() + " surge das sombras!");

            // loop de combate até a morte de algum deles
            while (heroi.getPontosDeVida() > 0 && monstroAtual.getPontosDeVida() > 0) {
                // herói ataca monstro
                heroi.atacar(monstroAtual);
                if (monstroAtual.getPontosDeVida() <= 0) {
                    System.out.println(monstroAtual.getNome() + " foi derrotado!");
                    heroi.ganharExperiencia(monstroAtual.getXpConcedido());
                    break; // Monstro derrotado
                }

                // monstro ataca
                monstroAtual.atacar(heroi);
                if (heroi.getPontosDeVida() <= 0) {
                    System.out.println("\n----------------------------------------------------");
                    System.out.println("GAME OVER! " + heroi.getNome() + " foi derrotado.");
                    System.out.println("----------------------------------------------------");
                    return; // kratos foi derrotado
                }

                // exibir status após cada luta
                heroi.exibirStatus();
                monstroAtual.exibirStatus();
            }
            heroi.exibirStatus();
        }

        System.out.println("\n----------------------------------------------------");
        System.out.println("Vitória! " + heroi.getNome() + " sobreviveu a todos os desafios dos deuses!");
        System.out.println("sua vingaça sera lembrada para sempre");
        System.out.println("----------------------------------------------------");
    }
}

