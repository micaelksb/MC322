// representa uma area ou etapa do jogo.
public interface Fase {
    
    void iniciar(Heroi heroi);

    boolean isConcluida();

    TipoCenario getTipoDeCenario();
}