
public class Guerreiro extends Heroi {
    private int furia;

    public Guerreiro(String nome, int pontosDeVida, int forca, int nivel, int experiencia, int furia) {
        super(nome, pontosDeVida, forca, nivel, experiencia);
        this.furia = furia;
    }

    @Override
    public void atacar(Personagem alvo) {
        // Calcula dano base
        int dano = this.forca + (this.furia / 2);
        
        // Adiciona dano da arma se equipada
        if (this.arma != null) {
            dano += this.arma.getDano();
        }
        
        System.out.println(this.nome + " ataca " + alvo.getNome() + " com a blade of chaos, causando " + dano + " de dano");
        alvo.receberDano(dano);
        this.furia += 5; // ganha fúria ao atacar
    }

    @Override
    public void usarHabilidadeEspecial(Personagem alvo) {
        if (this.furia >= 20) {
            int danoHabilidade = this.forca * 2;
            
            // Usa a sorte para determinar se erra o ataque
            if (Math.random() > this.sorte) {
                System.out.println(this.nome + " falhou no grito de guerra devido à baixa sorte!");
                this.furia -= 10; // perde menos fúria quando erra
            } else {
                System.out.println(this.nome + " usa grito de guerra, aumentando seu dano e causando " + danoHabilidade + " de dano em " + alvo.getNome() + "!");
                alvo.receberDano(danoHabilidade);
                this.furia -= 20;
            }
        } else {
            System.out.println(this.nome + " não tem fúria suficiente para usar o modo espartano (especial)");
        }
    }

    @Override
    public void exibirStatus() {
        super.exibirStatus();
        System.out.println("furia: " + this.furia);
    }
}