public abstract class Personagem {
    protected String nome;
    protected int pontosDeVida;
    protected int forca;
    protected Arma arma;

    public Personagem(String nome, int pontosDeVida, int forca) {
        this.nome = nome;
        this.pontosDeVida = pontosDeVida;
        this.forca = forca;
        this.arma = null; // Inicialmente sem arma
    }

    public void receberDano(int dano) {
        this.pontosDeVida -= dano;
        if (this.pontosDeVida < 0) {
            this.pontosDeVida = 0;
        }
        System.out.println(this.nome + " recebeu " + dano + " de dano. Vida restante: " + this.pontosDeVida);
    }

    public void exibirStatus() {
        System.out.println("\n--- Status de " + this.nome + " ---");
        System.out.println("Pontos de Vida: " + this.pontosDeVida);
        System.out.println("Força: " + this.forca);
        if (arma != null) {
            System.out.println("Arma: " + arma.getClass().getSimpleName() + " (Dano: " + arma.getDano() + ")");
        } else {
            System.out.println("Arma: Nenhuma");
        }
    }

    public abstract void atacar(Personagem alvo);

    // Getters
    public String getNome() {
        return nome;
    }

    public int getPontosDeVida() {
        return pontosDeVida;
    }

    public int getForca() {
        return forca;
    }

    public Arma getArma() {
        return arma;
    }

    // Setter para arma
    public void setArma(Arma arma) {
        this.arma = arma;
    }
}