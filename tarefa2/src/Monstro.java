
import java.util.ArrayList;
import java.util.Random;

public abstract class Monstro extends Personagem {
    protected int xpConcedido;
    protected ArrayList<Arma> listaDeArmasParaLargar;

    public Monstro(String nome, int pontosDeVida, int forca, int xpConcedido) {
        super(nome, pontosDeVida, forca);
        this.xpConcedido = xpConcedido;
        this.listaDeArmasParaLargar = new ArrayList<Arma>();
        
        // Inicializa com algumas armas básicas
        inicializarArmas();
    }
    
    private void inicializarArmas() {
        // Adiciona armas que este monstro pode largar
        listaDeArmasParaLargar.add(new PergaminhoDoEnigma());
        listaDeArmasParaLargar.add(new LaminasDoCaos());
        listaDeArmasParaLargar.add(new Tridente());
    }

    @Override
    public void exibirStatus() {
        super.exibirStatus();
        System.out.println("exp dropado ao ser derrotado: " + this.xpConcedido);
    }

    public Arma largaArma() {
        if (listaDeArmasParaLargar.isEmpty()) {
            return null;
        }
        
        Random random = new Random();
        int indiceAleatorio = random.nextInt(listaDeArmasParaLargar.size());
        return listaDeArmasParaLargar.get(indiceAleatorio);
    }

    // Getter para xpConcedido
    public int getXpConcedido() {
        return xpConcedido;
    }
}