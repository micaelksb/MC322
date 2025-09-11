
public class Esfinge extends Monstro {
    private int resistencia;

    public Esfinge(String nome, int pontosDeVida, int forca, int xpConcedido, int resistencia) {
        super(nome, pontosDeVida, forca, xpConcedido);
        this.resistencia = resistencia;
    }

    @Override
    public void atacar(Personagem alvo) {
        // Calcula dano base
        int dano = this.forca + (this.resistencia / 4);
        
        // Adiciona dano da arma se equipada
        if (this.arma != null) {
            dano += this.arma.getDano();
        }
        
        System.out.println(this.nome + " esmaga " + alvo.getNome() + " com um golpe brutal, causando " + dano + " de dano.");
        alvo.receberDano(dano);
    }

    @Override
    public void exibirStatus() {
        super.exibirStatus();
        System.out.println("Resistencia: " + this.resistencia);
    }
}