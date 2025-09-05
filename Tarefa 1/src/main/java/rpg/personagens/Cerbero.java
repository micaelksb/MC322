package rpg.personagens;

public class Cerbero extends Monstro {
    private int defesaOssea;

    public Cerbero(String nome, int pontosDeVida, int forca, int xpConcedido, int defesaOssea) {
        super(nome, pontosDeVida, forca, xpConcedido);
        this.defesaOssea = defesaOssea;
    }

    @Override
    public void atacar(Personagem alvo) {
        int dano = this.forca + (this.defesaOssea / 5); // Cerbero usa força e defesa de fogo
        System.out.println(this.nome + " ataca " + alvo.getNome() + " com um ataque de fogo, causando " + dano + " de dano.");
        alvo.receberDano(dano);
    }

    @Override
    public void exibirStatus() {
        super.exibirStatus();
        System.out.println("Defesa ígnea: " + this.defesaOssea);
    }
}

