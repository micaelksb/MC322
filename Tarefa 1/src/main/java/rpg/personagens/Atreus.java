package rpg.personagens;

public class Atreus extends Heroi {
    private int mana;

    public Atreus(String nome, int pontosDeVida, int forca, int nivel, int experiencia, int mana) {
        super(nome, pontosDeVida, forca, nivel, experiencia);
        this.mana = mana;
    }

    @Override
    public void atacar(Personagem alvo) {
        int dano = this.forca / 2; // ataque físico fraco
        System.out.println(this.nome + " ataca " + alvo.getNome() + " com uma magia, causando " + dano + " de dano.");
        alvo.receberDano(dano);
    }

    @Override
    public void usarHabilidadeEspecial(Personagem alvo) {
        if (this.mana >= 30) {
            int danoMagico = this.nivel * 5; // dano mágico baseado no nível
            System.out.println(this.nome + " lança uma bola de raio em " + alvo.getNome() + ", causando " + danoMagico + " de dano mágico");
            alvo.receberDano(danoMagico);
            this.mana -= 30;
        } else {
            System.out.println(this.nome + " não tem mana suficiente para usar a habilidade especial!");
        }
    }

    @Override
    public void exibirStatus() {
        super.exibirStatus();
        System.out.println("Mana: " + this.mana);
    }
}

