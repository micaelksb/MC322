package rpg.personagens;

public abstract class Monstro extends Personagem {
    protected int xpConcedido;

    public Monstro(String nome, int pontosDeVida, int forca, int xpConcedido) {
        super(nome, pontosDeVida, forca);
        this.xpConcedido = xpConcedido;
    }

    @Override
    public void exibirStatus() {
        super.exibirStatus();
        System.out.println("exp dropado ao ser derrotado: " + this.xpConcedido);
    }

    // Getter para xpConcedido
    public int getXpConcedido() {
        return xpConcedido;
    }
}

