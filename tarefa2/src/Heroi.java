public abstract class Heroi extends Personagem {
    protected int nivel;
    protected int experiencia;
    protected int expProximoNivel;
    protected double sorte;

    public Heroi(String nome, int pontosDeVida, int forca, int nivel, int experiencia) {
        super(nome, pontosDeVida, forca);
        this.nivel = nivel;
        this.experiencia = experiencia;
        this.expProximoNivel = 100 * nivel; // Experiência necessária para o próximo nível
        this.sorte = Math.random(); // Valor entre 0 e 1
    }

    public void ganharExperiencia(int xp) {
        this.experiencia += xp;
        System.out.println(this.nome + " ganhou " + xp + " de experiência.");
        
        // Verifica se pode subir de nível
        if (this.experiencia >= this.expProximoNivel) {
            subirDeNivel();
        }
    }

    private void subirDeNivel() {
        this.nivel++;
        System.out.println(this.nome + " subiu para o nível " + this.nivel + "!");
        
        // Atualiza experiência necessária para o próximo nível
        this.expProximoNivel = 100 * this.nivel;
        
        // Fortalece os atributos atuais
        this.pontosDeVida += 10;
        this.forca += 2;
        
        // Atualiza sorte
        this.sorte = Math.random();
    }

    @Override
    public void exibirStatus() {
        super.exibirStatus();
        System.out.println("Nível: " + this.nivel);
        System.out.println("Experiência: " + this.experiencia + "/" + this.expProximoNivel);
        System.out.printf("Sorte: %.2f\n", this.sorte);
    }

    public void equiparArma(Arma novaArma) {
        if (novaArma.getMinNivel() <= this.nivel) {
            this.arma = novaArma;
            System.out.println(this.nome + " equipou " + novaArma.getClass().getSimpleName() + "!");
        } else {
            System.out.println(this.nome + " não tem nível suficiente para usar " + novaArma.getClass().getSimpleName() + 
                             " (requer nível " + novaArma.getMinNivel() + ")");
        }
    }

    public abstract void usarHabilidadeEspecial(Personagem alvo);

    // Getters adicionais
    public int getNivel() {
        return nivel;
    }

    public int getExperiencia() {
        return experiencia;
    }

    public int getExpProximoNivel() {
        return expProximoNivel;
    }

    public double getSorte() {
        return sorte;
    }
}