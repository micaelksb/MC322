import java.util.List;

public class Main {
    public static void main(String[] args) {

        GeradorDeFases gerador = new ConstrutorDeCenarioFixo();
        Heroi heroi = new Guerreiro("Kratos", 100, 20, 1, 0, 10);

        List<Fase> fases = gerador.gerar(3);

        System.out.println("----- DESAFIO DE SOBREVIVÊNCIA -----");
        heroi.exibirStatus();

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

                    AcaoDeCombate acaoHeroi = heroi.escolherAcao(monstro);
                    acaoHeroi.executar(heroi, monstro);

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

                    AcaoDeCombate acaoMonstro = monstro.escolherAcao(heroi);
                    acaoMonstro.executar(monstro, heroi);

                    if (!heroi.estaVivo()) {
                        System.out.println("\n" + "=".repeat(30));
                        System.out.println("      GAME OVER      ");
                        System.out.println(heroi.getNome() + " foi derrotado!");
                        System.out.println("=".repeat(30));
                        return; // fim do jogo
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