public class Guerreiro extends Heroi {
    private int furia;

    public Guerreiro(String nome, int pontosDeVida, int forca, int nivel, int experiencia, int furia) {
        super(nome, pontosDeVida, forca, nivel, experiencia);
        this.furia = furia;
        
        // preenche a lista de ações com as habilidades
        this.acoes.add(new AtaqueFisico());
        this.acoes.add(new FuriaEspartana());
    }
    
    @Override
    protected int calcularDanoBase() {
        return this.forca + (this.furia / 2);
    }

    @Override
    public void exibirStatus() {
        super.exibirStatus();
        System.out.println("Furia: " + this.furia);
    }
    
    // Getters e Setters para a Fúria
    public int getFuria() {
        return furia;
    }

    public void setFuria(int furia) {
        this.furia = furia;
    }
}