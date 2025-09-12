import java.util.ArrayList;

/**
 * Classe utilizada para guardar os métodos estáticos necessários para a execução 
 * de partes do RPG relacionadas à definição dos cenários onde a história se passa.
 */
public class ConstrutorDeCenario {
    
    /**
     * Cria "n" fases de acordo com o parâmetro do método.
     * Retorna uma lista de Fases com dificuldade crescente (nível da fase).
     * parâmetro: nFases número de fases a serem criadas
     * retorna: lista de Fases com dificuldade crescente
     */
    public static ArrayList<Fase> gerarFases(int nFases) {
        ArrayList<Fase> fases = new ArrayList<Fase>();
        
        String[] ambientes = {"Caverna dos Ciclopes", "Labirinto da Esfinge", "Portal do Submundo"};
        
        for (int i = 1; i <= nFases; i++) {
            // Criar lista de monstros para a fase com dificuldade baseada no nível
            ArrayList<Monstro> monstrosDaFase = new ArrayList<Monstro>();
            
            // Criar monstros com dificuldade crescente baseada no nível da fase
            int vidaBase = 30 + (i * 15); // Vida aumenta com o nível da fase
            int forcaBase = 8 + (i * 3);  // Força aumenta com o nível da fase
            int xpBase = 15 + (i * 5);    // XP concedido aumenta com o nível
            
            // Adicionar 2-3 monstros por fase
            monstrosDaFase.add(new Ciclope("Ciclope Lvl " + i, vidaBase, forcaBase, xpBase, 10 + i));
            monstrosDaFase.add(new Esfinge("Esfinge Lvl " + i, vidaBase + 10, forcaBase + 2, xpBase + 5, 8 + i));
            
            if (i >= 2) { // A partir da fase 2, adiciona Cerbero
                monstrosDaFase.add(new Cerbero("Cerbero Lvl " + i, vidaBase + 20, forcaBase + 3, xpBase + 10, 12 + i));
            }
            
            String ambiente = (i <= ambientes.length) ? ambientes[i-1] : "Fase " + i;
            
            Fase fase = new Fase(i, ambiente, monstrosDaFase);
            fases.add(fase);
        }
        
        return fases;
    }
}