package rpg.personagens;

public class Esfinge extends Monstro {
    private int resistencia;

    public Esfinge(String nome, int pontosDeVida, int forca, int xpConcedido, int resistencia) {
        super(nome, pontosDeVida, forca, xpConcedido);
        this.resistencia = resistencia;
    }

    @Override
    public void atacar(Personagem alvo) {
        int dano = this.forca + (this.resistencia / 4); // Orc usa força e resistência
        System.out.println(this.nome + " esmaga " + alvo.getNome() + " com um golpe brutal, causando " + dano + " de dano.");
        alvo.receberDano(dano);
    }

    @Override
    public void exibirStatus() {
        super.exibirStatus();
        System.out.println("Resistencia: " + this.resistencia);
    }
}

