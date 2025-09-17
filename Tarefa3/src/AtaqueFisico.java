/// representa uma ação de ataque físico normal

public class AtaqueFisico implements AcaoDeCombate {

    @Override
    public void executar(Combatente usuario, Combatente alvo) {
        // para garantir que o usuário é um Personagem.
        if (usuario instanceof Personagem) {
            Personagem pUsuario = (Personagem) usuario;

            int danoBase = pUsuario.calcularDanoBase();
            if (pUsuario.getArma() != null) {
                danoBase += pUsuario.getArma().getDano();
            }

            System.out.println(usuario.getNome() + " desfere um golpe em " + alvo.getNome() + "!");
            alvo.receberDano(danoBase);
        }
    }
}