package rpg.personagens;

public abstract class Heroi extends Personagem {
    protected int nivel;
    protected int experiencia;

    public Heroi(String nome, int pontosDeVida, int forca, int nivel, int experiencia) {
        super(nome, pontosDeVida, forca);
        this.nivel = nivel;
        this.experiencia = experiencia;
    }

    public void ganharExperiencia(int xp) {
        this.experiencia += xp;
        System.out.println(this.nome + " ganhou " + xp + " de experiência.");
        // para subir de nível
        if (this.experiencia >= 100 * this.nivel) { // 100 de exp para subir
            this.nivel++;
            System.out.println(this.nome + " subiu para o nível " + this.nivel + "!");
            // Aumenta atributos ao subir de nível
            this.pontosDeVida += 10;
            this.forca += 2;
        }
    }

    @Override
    public void exibirStatus() {
        super.exibirStatus();
        System.out.println("Nível: " + this.nivel);
        System.out.println("Experiência: " + this.experiencia);
    }

    public abstract void usarHabilidadeEspecial(Personagem alvo);

    // Getters adicionais
    public int getNivel() {
        return nivel;
    }

    public int getExperiencia() {
        return experiencia;
    }
}

