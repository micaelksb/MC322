
public class Atreus extends Heroi {
    private int mana;

    public Atreus(String nome, int pontosDeVida, int forca, int nivel, int experiencia, int mana) {
        super(nome, pontosDeVida, forca, nivel, experiencia);
        this.mana = mana;
    }

    @Override
    public void atacar(Personagem alvo) {
        // Calcula dano base
        int dano = this.forca / 2;
        
        // Adiciona dano da arma se equipada
        if (this.arma != null) {
            dano += this.arma.getDano();
        }
        
        System.out.println(this.nome + " ataca " + alvo.getNome() + " com uma magia, causando " + dano + " de dano.");
        alvo.receberDano(dano);
    }

    @Override
    public void usarHabilidadeEspecial(Personagem alvo) {
        if (this.mana >= 30) {
            int danoMagico = this.nivel * 5; // dano mágico baseado no nível
            
            // Usa a sorte para determinar se o ataque é crítico
            if (Math.random() < this.sorte) {
                danoMagico *= 2; // Dano crítico
                System.out.println(this.nome + " teve sorte! Bola de raio crítica em " + alvo.getNome() + ", causando " + danoMagico + " de dano mágico crítico!");
            } else {
                System.out.println(this.nome + " lança uma bola de raio em " + alvo.getNome() + ", causando " + danoMagico + " de dano mágico");
            }
            
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