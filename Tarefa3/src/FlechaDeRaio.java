// habilidade especial do atreus
public class FlechaDeRaio implements AcaoDeCombate {

    private static final int CUSTO_MANA = 30;

    @Override
    public void executar(Combatente usuario, Combatente alvo) {
        if (usuario instanceof Atreus) {
            Atreus atreus = (Atreus) usuario;

            if (atreus.getMana() >= CUSTO_MANA) {
                int danoMagico = atreus.getNivel() * 5;

                if (Util.gerarNumeroAleatorio() < atreus.getSorte()) {
                    danoMagico *= 2; // dano crítico
                    System.out.println(atreus.getNome() + " teve sorte! Flecha de Raio crítica em " + alvo.getNome() + ", causando " + danoMagico + " de dano!");
                } else {
                    System.out.println(atreus.getNome() + " lança uma Flecha de Raio em " + alvo.getNome() + ", causando " + danoMagico + " de dano.");
                }

                alvo.receberDano(danoMagico);
                atreus.setMana(atreus.getMana() - CUSTO_MANA);
            } else {
                System.out.println(atreus.getNome() + " não tem mana suficiente!");
                new AtaqueFisico().executar(usuario, alvo); // ataca normalmente para não perder o turno
            }
        }
    }
}