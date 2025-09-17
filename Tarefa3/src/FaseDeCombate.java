import java.util.List;

public class FaseDeCombate implements Fase {
    private TipoCenario tipoDeCenario;
    private List<Monstro> monstros;

    public FaseDeCombate(TipoCenario tipoDeCenario, List<Monstro> monstros) {
        this.tipoDeCenario = tipoDeCenario;
        this.monstros = monstros;
    }

    @Override
    public void iniciar(Heroi heroi) {
        this.tipoDeCenario.aplicarEfeitos(heroi);
    }

    @Override
    public boolean isConcluida() {
        // a fase esta concluída se todos os monstros foram derrotados
        for (Monstro m : monstros) {
            if (m.estaVivo()) {
                return false; // se encontrar um monstro vivo, não está concluída
            }
        }
        return true;
    }

    @Override
    public TipoCenario getTipoDeCenario() {
        return this.tipoDeCenario;
    }
    
    public List<Monstro> getMonstros() {
        return monstros;
    }
}