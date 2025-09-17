// especial furia espartana do kratos
public class FuriaEspartana implements AcaoDeCombate {

    @Override
    public void executar(Combatente usuario, Combatente alvo) {
        if (usuario instanceof Guerreiro) {
            Guerreiro guerreiro = (Guerreiro) usuario;
            
            if (guerreiro.getFuria() >= 20) {
                int danoHabilidade = guerreiro.getForca() * 2;
                
                if (Util.gerarNumeroAleatorio() > guerreiro.getSorte()) {
                    System.out.println(guerreiro.getNome() + " falhou no grito de guerra devido a baixa sorte");
                    guerreiro.setFuria(guerreiro.getFuria() - 10);
                } else {
                    System.out.println(guerreiro.getNome() + "usa Fúria Espartana, causando" + danoHabilidade + " de dano em " + alvo.getNome() + "!");
                    alvo.receberDano(danoHabilidade);
                    guerreiro.setFuria(guerreiro.getFuria() - 20);
                }
            } else {
                System.out.println(guerreiro.getNome() + " não tem fúria suficiente!");
                new AtaqueFisico().executar(usuario, alvo);
            }
        }
    }
}