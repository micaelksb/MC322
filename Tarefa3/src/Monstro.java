import java.util.ArrayList;

public abstract class Monstro extends Personagem implements Lootavel {
    protected int xpConcedido;
    protected ArrayList<Arma> listaDeArmasParaLargar;

    public Monstro(String nome, int pontosDeVida, int forca, int xpConcedido) {
        super(nome, pontosDeVida, forca);
        this.xpConcedido = xpConcedido;
        this.listaDeArmasParaLargar = new ArrayList<>();
        this.acoes = new ArrayList<>();
        inicializarArmas();
        inicializarAcoes();
    }
    
    // define as ações que todos os monstros podem realizar
    private void inicializarAcoes() {
        this.acoes.add(new AtaqueFisico());
    }
    
    @Override
    public AcaoDeCombate escolherAcao(Combatente alvo) {
        System.out.println(this.nome + " se prepara para atacar!");
        return this.acoes.get(0);
    }
    
    @Override
    public Item droparLoot() {
        if (listaDeArmasParaLargar.isEmpty()) {
            return null;
        }
        int indiceAleatorio = Util.gerarNumeroAleatorio(listaDeArmasParaLargar.size());
        return listaDeArmasParaLargar.get(indiceAleatorio);
    }
    
    private void inicializarArmas() {
        listaDeArmasParaLargar.add(new PergaminhoDoEnigma());
        listaDeArmasParaLargar.add(new LaminasDoCaos());
        listaDeArmasParaLargar.add(new Tridente());
    }

    @Override
    public void exibirStatus() {
        super.exibirStatus();
        System.out.println("EXP Concedido: " + this.xpConcedido);
    }
    
    public int getXpConcedido() {
        return xpConcedido;
    }
    
}