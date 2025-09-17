import java.util.ArrayList;
import java.util.List;

public class ConstrutorDeCenarioFixo implements GeradorDeFases {

    @Override
    public List<Fase> gerar(int quantidadeDeFases) {
        List<Fase> fases = new ArrayList<>();
        
        TipoCenario[] cenarios = {TipoCenario.MIDGARD, TipoCenario.ALFHEIM, TipoCenario.SVARTALFHEIM};
        
        for (int i = 1; i <= quantidadeDeFases; i++) {
            List<Monstro> monstrosDaFase = new ArrayList<>();
            
            int vidaBase = 30 + (i * 15);
            int forcaBase = 8 + (i * 3);
            int xpBase = 15 + (i * 5);
            
            monstrosDaFase.add(new Ciclope("Ciclope Lvl " + i, vidaBase, forcaBase, xpBase, 10 + i));
            monstrosDaFase.add(new Esfinge("Esfinge Lvl " + i, vidaBase + 10, forcaBase + 2, xpBase + 5, 8 + i));
            
            if (i >= 2) {
                monstrosDaFase.add(new Cerbero("Cerbero Lvl " + i, vidaBase + 20, forcaBase + 3, xpBase + 10, 12 + i));
            }
            
            TipoCenario cenario = (i <= cenarios.length) ? cenarios[i-1] : TipoCenario.MIDGARD;
            
            Fase fase = new FaseDeCombate(cenario, monstrosDaFase);
            fases.add(fase);
        }
        
        return fases;
    }
}