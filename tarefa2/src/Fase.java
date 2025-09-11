import java.util.ArrayList;

public class Fase {
    private int nivel;
    private String ambiente;
    private ArrayList<Monstro> monstros;

    /**
     * Construtor para inicializar os atributos
     */
    public Fase(int nivel, String ambiente, ArrayList<Monstro> monstros) {
        this.nivel = nivel;
        this.ambiente = ambiente;
        this.monstros = monstros;
    }

    // Getters necessários para acessar os atributos
    public int getNivel() {
        return nivel;
    }

    public String getAmbiente() {
        return ambiente;
    }

    public ArrayList<Monstro> getMonstros() {
        return monstros;
    }
}