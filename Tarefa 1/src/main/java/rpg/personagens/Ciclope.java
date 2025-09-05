package rpg.personagens;

public class Ciclope extends Monstro {
    private int agilidade;

    public Ciclope(String nome, int pontosDeVida, int forca, int xpConcedido, int agilidade) {
        super(nome, pontosDeVida, forca, xpConcedido);
        this.agilidade = agilidade;
    }

    @Override
    public void atacar(Personagem alvo) {
        int dano = this.forca + (this.agilidade / 3);
        System.out.println(this.nome + " ataca " + alvo.getNome() + " com uma investida rápida, causando " + dano + " de dano.");
        alvo.receberDano(dano);
    }

    @Override
    public void exibirStatus() {
        super.exibirStatus();
        System.out.println("Agilidade: " + this.agilidade);
    }
}

