
public class Ciclope extends Monstro {
    private int agilidade;

    public Ciclope(String nome, int pontosDeVida, int forca, int xpConcedido, int agilidade) {
        super(nome, pontosDeVida, forca, xpConcedido);
        this.agilidade = agilidade;
    }

    @Override
    public void atacar(Personagem alvo) {
        // Calcula dano base
        int dano = this.forca + (this.agilidade / 3);
        
        // Adiciona dano da arma se equipada
        if (this.arma != null) {
            dano += this.arma.getDano();
        }
        
        System.out.println(this.nome + " ataca " + alvo.getNome() + " com uma investida rápida, causando " + dano + " de dano.");
        alvo.receberDano(dano);
    }

    @Override
    public void exibirStatus() {
        super.exibirStatus();
        System.out.println("Agilidade: " + this.agilidade);
    }
}