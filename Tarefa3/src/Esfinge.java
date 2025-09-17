public class Esfinge extends Monstro {
    private int resistencia;

    public Esfinge(String nome, int pontosDeVida, int forca, int xpConcedido, int resistencia) {
        super(nome, pontosDeVida, forca, xpConcedido);
        this.resistencia = resistencia;
    }

    @Override
    protected int calcularDanoBase() {
        return this.forca + (this.resistencia / 4);
    }

    @Override
    public void exibirStatus() {
        super.exibirStatus();
        System.out.println("Resistencia: " + this.resistencia);
    }
}