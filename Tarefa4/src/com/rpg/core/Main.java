package com.rpg.core;

import java.util.List;
import com.rpg.personagens.herois.Guerreiro;
import com.rpg.personagens.Heroi;
import com.rpg.personagens.Monstro;
import com.rpg.itens.Lootavel;
import com.rpg.cenario.GeradorDeFases;
import com.rpg.cenario.ConstrutorDeCenarioFixo;
import com.rpg.cenario.Fase;
import com.rpg.cenario.TipoCenario;
import com.rpg.combate.FaseDeCombate;
import com.rpg.combate.AcaoDeCombate;
import com.rpg.itens.Arma;
import com.rpg.itens.Item;
import com.rpg.utils.InputManager;
import com.rpg.exceptions.NivelInsuficienteException;
import com.rpg.exceptions.RecursoInsuficienteException;
import com.rpg.utils.DisplayManager;

public class Main {
    public static void main(String[] args) {

        boolean sair = false;
        while (!sair) {
            System.out.println("\nTERRAS SOMBRIAS - RPG");
            System.out.println("========================================");
            System.out.println("[1] Iniciar Novo Jogo");
            System.out.println("[2] Ver Informações das Classes de Heróis");
            System.out.println("[3] Ver Informações das Classes de Monstros");
            System.out.println("[4] Sair do Jogo");
            System.out.println("========================================");

            int opcao = InputManager.lerInteiro("Digite sua opção > ", 1, 4);

            switch (opcao) {
                case 1:
                    iniciarNovoJogo();
                    break;
                case 2:
                    DisplayManager.exibirInformacoesHerois();
                    InputManager.esperarEnter("\nPressione Enter para voltar ao menu.");
                    break;
                case 3:
                    DisplayManager.exibirInformacoesMonstros();
                    InputManager.esperarEnter("\nPressione Enter para voltar ao menu.");
                    break;
                case 4:
                    sair = true;
                    break;
            }
        }
        InputManager.fecharScanner();
    }

    public static void iniciarNovoJogo() {
        System.out.println("\n----- INICIANDO NOVO JOGO -----");

        // Escolha do Herói
        System.out.println("Escolha seu Herói:");
        System.out.println("1. Kratos (Guerreiro)");
        System.out.println("2. Atreus (Arqueiro)");
        int escolhaHeroi = InputManager.lerInteiro("Digite o número do seu herói: ", 1, 2);

        Heroi heroi;
        if (escolhaHeroi == 1) {
            heroi = new Guerreiro("Kratos", 100, 20, 1, 0, 10);
        } else {
            heroi = new com.rpg.personagens.herois.Atreus("Atreus", 80, 15, 1, 0, 12);
        }

        // Escolha da Dificuldade
        System.out.println("\nEscolha a Dificuldade:");
        System.out.println("1. Fácil");
        System.out.println("2. Normal");
        System.out.println("3. Difícil");
        int escolhaDificuldade = InputManager.lerInteiro("Digite o número da dificuldade: ", 1, 3);

        Dificuldade dificuldade;
        switch (escolhaDificuldade) {
            case 1:
                dificuldade = Dificuldade.FACIL;
                break;
            case 2:
                dificuldade = Dificuldade.NORMAL;
                break;
            case 3:
                dificuldade = Dificuldade.DIFICIL;
                break;
            default:
                dificuldade = Dificuldade.NORMAL; // Padrão
        }

        // Escolha da quantidade de fases
        int quantidadeDeFases = InputManager.lerInteiro("Quantas fases você deseja jogar? (1-5): ", 1, 5);

        GeradorDeFases gerador = new ConstrutorDeCenarioFixo();
        List<Fase> fases = gerador.gerar(quantidadeDeFases, dificuldade);

        System.out.println("\nHerói escolhido:");
        heroi.exibirStatus();
        InputManager.esperarEnter("\nPressione Enter para começar a aventura!");

        // loop principal do jogo, iterando sobre as fases
        for (Fase faseAtual : fases) {
            FaseDeCombate faseDeCombate = (FaseDeCombate) faseAtual;

            System.out.println("\n" + "=".repeat(50));
            System.out.println("KRATOS ENTRA EM " + faseDeCombate.getTipoDeCenario().getDescricao().toUpperCase());
            System.out.println("=".repeat(50));
            
            faseAtual.iniciar(heroi);
            
            // loop de combate para cada monstro na fase
            for (Monstro monstro : faseDeCombate.getMonstros()) {
                System.out.println("\n--- UM INIMIGO SURGE ---");
                monstro.exibirStatus();

                while (heroi.estaVivo() && monstro.estaVivo()) {
                    System.out.println("\n--- INÍCIO DO TURNO ---");

                    AcaoDeCombate acaoHeroi = null;
                    try {
                        acaoHeroi = heroi.escolherAcao(monstro);
                        acaoHeroi.executar(heroi, monstro);
                    } catch (RecursoInsuficienteException e) {
                        System.out.println(e.getMessage());
                        // Se não tiver recurso, o herói faz um ataque físico básico
                        new com.rpg.combate.AtaqueFisico().executar(heroi, monstro);
                    }

                    if (!monstro.estaVivo()) {
                        System.out.println(monstro.getNome() + " foi derrotado!");
                        heroi.ganharExperiencia(monstro.getXpConcedido());

                        // verifica se o monstro é lootavel e tenta dropar um item.
                        if (monstro instanceof Lootavel) {
                            Item loot = ((Lootavel) monstro).droparLoot();
                            if (loot != null && loot instanceof Arma) {
                                System.out.println(monstro.getNome() + " deixou uma recompensa!");
                                heroi.avaliarTrocaDeArma((Arma) loot);
                            }
                        }
                        break; 
                    }

                    AcaoDeCombate acaoMonstro = null;
                    try {
                        acaoMonstro = monstro.escolherAcao(heroi);
                        acaoMonstro.executar(monstro, heroi);
                    } catch (RecursoInsuficienteException e) {
                        System.out.println(e.getMessage());
                        // Monstros não deveriam ter exceções de recurso, mas para robustez
                        new com.rpg.combate.AtaqueFisico().executar(monstro, heroi);
                    }

                    if (!heroi.estaVivo()) {
                        System.out.println("\n" + "=".repeat(30));
                        System.out.println("      GAME OVER      ");
                        System.out.println(heroi.getNome() + " foi derrotado!");
                        System.out.println("=".repeat(30));
                        return; // fim do jogo
                    }
                }
                // Menu pós-turno
                if (heroi.estaVivo()) {
                    boolean continuarTurno = false;
                    while (!continuarTurno) {
                        System.out.println("\n--- FIM DO COMBATE COM " + monstro.getNome() + " ---");
                        System.out.println("[1] Interagir com o Loot (se houver)");
                        System.out.println("[2] Ver Informações do Personagem");
                        System.out.println("[3] Desistir do Jogo");
                        int opcaoPosTurno = InputManager.lerInteiro("Digite sua opção > ", 1, 3);

                        switch (opcaoPosTurno) {
                            case 1:
                                // Lógica de loot já está implementada acima, mas podemos adicionar mais interação aqui
                                System.out.println("Você já interagiu com o loot deste monstro automaticamente.");
                                break;
                            case 2:
                                DisplayManager.exibirStatusPersonagem(heroi);
                                break;
                            case 3:
                                System.out.println("Você desistiu do jogo. GAME OVER!");
                                return; // Encerra o jogo
                            default:
                                System.out.println("Opção inválida.");
                        }
                        if (opcaoPosTurno == 1 || opcaoPosTurno == 2) {
                            continuarTurno = InputManager.lerSimNao("Deseja continuar para o próximo combate/fase?");
                        } else if (opcaoPosTurno == 3) {
                            continuarTurno = true; // Sai do loop para encerrar o jogo
                        }
                    }
                }
            }

            if (faseAtual.isConcluida()) {
                System.out.println("\n--- FIM DA FASE ---");
                System.out.println("Todos os inimigos foram derrotados!");
            }
        }

        System.out.println("\n" + "=".repeat(50));
        System.out.println("        🏆 VITÓRIA! 🏆        ");
        System.out.println(heroi.getNome() + " sobreviveu a todos os desafios!");
        System.out.println("Parabéns, Semideus lendário!");
        System.out.println("=".repeat(50));
        heroi.exibirStatus();
    }
}

