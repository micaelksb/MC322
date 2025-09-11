public abstract class Arma {
    protected int dano;
    protected int minNivel;

    /**
     * Construtor para inicializar os atributos da arma
     * @param dano Quanto de dano extra esta arma dá ao Personagem
     * @param minNivel Nível mínimo que o Herói precisa ter para utilizar a Arma
     */
    public Arma(int dano, int minNivel) {
        this.dano = dano;
        this.minNivel = minNivel;
    }

    public int getDano() {
        return dano;
    }

    public int getMinNivel() {
        return minNivel;
    }
}