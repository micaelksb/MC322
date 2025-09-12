import java.util.ArrayList;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Random random = new Random();
        
        // Criação das Fases 
        ArrayList<Fase> fases = ConstrutorDeCenario.gerarFases(3);
        
        // Criação do Herói
        Guerreiro heroi = new Guerreiro("Kratos", 100, 20, 1, 0, 10);
        
        System.out.println("===== DESAFIO DE SOBREVIVÊNCIA =====");
        heroi.exibirStatus();
        
        // Simulação da aventura em Loops
        for (Fase faseAtual : fases) {
            // Apresentação do Desafio
            System.out.println("\n" + "=".repeat(50));
            System.out.println("O HERÓI ENTRA NA " + faseAtual.getAmbiente().toUpperCase() + 
                             " PARA ENFRENTAR " + faseAtual.getMonstros().size() + " MONSTROS!");
            System.out.println("=".repeat(50));
            
            // Anunciar detalhes da fase e do herói
            System.out.println("\n--- INÍCIO DA FASE " + faseAtual.getNivel() + " ---");
            heroi.exibirStatus();
            
            // Loop para cada monstro da fase
            for (Monstro monstro : faseAtual.getMonstros()) {
                System.out.println("\n--- CHEGADA DO INIMIGO ---");
                System.out.println("Um " + monstro.getNome() + " aparece!");
                monstro.exibirStatus();
                
                // Combate enquanto ambos estão vivos
                while (heroi.getPontosDeVida() > 0 && monstro.getPontosDeVida() > 0) {
                    System.out.println("\n--- INÍCIO DO TURNO ---");
                    
                    // Herói ataca primeiro
                    heroi.atacar(monstro);
                    
                    // Verifica se o monstro sobreviveu
                    if (monstro.getPontosDeVida() <= 0) {
                        System.out.println(monstro.getNome() + " foi derrotado!");
                        
                        // Herói ganha experiência
                        heroi.ganharExperiencia(monstro.getXpConcedido());
                        
                        // Testa a sorte do herói para largar arma
                        if (random.nextDouble() < heroi.getSorte()) {
                            System.out.println("Com sorte, " + heroi.getNome() + " encontra uma arma!");
                            Arma armaLargada = monstro.largaArma();
                            
                            if (armaLargada != null) {
                                System.out.println("Arma encontrada: " + armaLargada.getClass().getSimpleName());
                                
                                // Decisão simples: equipar se for melhor ou se não tiver arma
                                if (heroi.getArma() == null || armaLargada.getDano() > heroi.getArma().getDano()) {
                                    heroi.equiparArma(armaLargada);
                                } else {
                                    System.out.println(heroi.getNome() + " decide manter sua arma atual.");
                                }
                            }
                        } else {
                            System.out.println("Sem sorte desta vez, nenhuma arma foi encontrada.");
                        }
                        break;
                    }
                    
                    // Monstro ataca de volta
                    monstro.atacar(heroi);
                    
                    // Verifica se o herói sobreviveu
                    if (heroi.getPontosDeVida() <= 0) {
                        System.out.println("\n" + "=".repeat(30));
                        System.out.println("      GAME OVER!      ");
                        System.out.println(heroi.getNome() + " foi derrotado!");
                        System.out.println("=".repeat(30));
                        return; // Sai do programa
                    }
                }
            }
            
            System.out.println("\n--- FIM DA FASE " + faseAtual.getNivel() + " ---");
            System.out.println("Fase completada com sucesso!");
        }
        
        // Conclusão do Desafio - Vitória
        System.out.println("\n" + "=".repeat(50));
        System.out.println("        🏆 VITÓRIA! 🏆        ");
        System.out.println(heroi.getNome() + " sobreviveu a todas as fases!");
        System.out.println("Parabéns, herói lendário!");
        System.out.println("=".repeat(50));
        heroi.exibirStatus();
    }
}