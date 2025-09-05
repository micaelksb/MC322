package rpg.personagens;

public abstract class Personagem {
    protected String nome;
    protected int pontosDeVida;
    protected int forca;

    public Personagem(String nome, int pontosDeVida, int forca) {
        this.nome = nome;
        this.pontosDeVida = pontosDeVida;
        this.forca = forca;
    }

    public void receberDano(int dano) {
        this.pontosDeVida -= dano;
        if (this.pontosDeVida < 0) {
            this.pontosDeVida = 0;
        }
        System.out.println(this.nome + " recebeu " + dano + " de dano. Vida restantes: " + this.pontosDeVida);
    }

    public void exibirStatus() {
        System.out.println("\n--- Status de " + this.nome + " ---");
        System.out.println("Pontos de Vida: " + this.pontosDeVida);
        System.out.println("Força: " + this.forca);
    }

    public abstract void atacar(Personagem alvo);

    // Getters para acesso aos atributos, se necessário
    public String getNome() {
        return nome;
    }

    public int getPontosDeVida() {
        return pontosDeVida;
    }

    public int getForca() {
        return forca;
    }
}

