package com.rpg.personagens;

import com.rpg.combate.Combatente;
import com.rpg.combate.AcaoDeCombate;
import com.rpg.itens.Arma;
import com.rpg.exceptions.NivelInsuficienteException;
import com.rpg.utils.Util;
import java.util.ArrayList;

public abstract class Heroi extends Personagem {
    private static final int EXPERIENCIA_BASE_PROXIMO_NIVEL = 100;
    private static final int BONUS_VIDA_POR_NIVEL = 10;
    private static final int BONUS_FORCA_POR_NIVEL = 2;

    protected int nivel;
    protected int experiencia;
    protected int expProximoNivel;
    protected double sorte;

    public Heroi(String nome, int pontosDeVida, int forca, int nivel, int experiencia) {
        super(nome, pontosDeVida, forca);
        this.nivel = nivel;
        this.experiencia = experiencia;
        this.expProximoNivel = EXPERIENCIA_BASE_PROXIMO_NIVEL * nivel;
        this.sorte = Util.gerarNumeroAleatorio();
        this.acoes = new ArrayList<>(); // Inicializa a lista de ações
    }

    @Override
    public AcaoDeCombate escolherAcao(Combatente alvo) {
        // simulação: 80% de chance de usar um ataque normal e 20% de usar a habilidade especial
        // a primeira ação (indice 0) será o ataque normal, a segunda (indice 1) será o especial
        if (Util.gerarNumeroAleatorio() < 0.8 || acoes.size() < 2) {
            System.out.println(this.nome + " escolhe um ataque físico!");
            return this.acoes.get(0);
        } else {
            System.out.println(this.nome + " decide usar uma habilidade especial!");
            return this.acoes.get(1);
        }
    }

    /**
     * Adiciona experiência ao herói e verifica se ele pode subir de nível.
     * @param xp A quantidade de experiência ganha.
     */
    public void ganharExperiencia(int xp) {
        this.experiencia += xp;
        System.out.println(this.nome + " ganhou " + xp + " de experiência.");
        if (this.experiencia >= this.expProximoNivel) {
            subirDeNivel();
        }
    }
    private void subirDeNivel() {
        this.nivel++;
        System.out.println(this.nome + " subiu para o nível " + this.nivel + "!");
        this.expProximoNivel = EXPERIENCIA_BASE_PROXIMO_NIVEL * this.nivel;
        this.pontosDeVida += BONUS_VIDA_POR_NIVEL;
        this.forca += BONUS_FORCA_POR_NIVEL;
        this.sorte = Util.gerarNumeroAleatorio();
    }
    @Override
    public void exibirStatus() {
        super.exibirStatus();
        System.out.println("Nível: " + this.nivel);
        System.out.println("Experiência: " + this.experiencia + "/" + this.expProximoNivel);
        System.out.printf("Sorte: %.2f\n", this.sorte);
    }

    /**
     * Equipa uma nova arma no herói, se ele tiver o nível mínimo necessário.
     * @param novaArma A arma a ser equipada.
     * @throws NivelInsuficienteException se o nível do herói for menor que o nível mínimo da arma.
     */
    public void equiparArma(Arma novaArma) throws NivelInsuficienteException {
        if (novaArma.getMinNivel() <= this.nivel) {
            this.arma = novaArma;
            System.out.println(this.nome + " equipou " + novaArma.getClass().getSimpleName() + "!");
        } else {
            throw new NivelInsuficienteException(this.nome + " não tem nível suficiente para usar " + novaArma.getClass().getSimpleName() + " (requer nível " + novaArma.getMinNivel() + ")");
        }
    }
    public void avaliarTrocaDeArma(Arma novaArma) {
        System.out.println("Arma encontrada: " + novaArma.getNome());
        if (this.arma == null || novaArma.getDano() > this.arma.getDano()) {
            System.out.println(this.nome + " decide que a nova arma é melhor.");
            try {
                equiparArma(novaArma);
            } catch (NivelInsuficienteException e) {
                System.out.println(e.getMessage());
            }
        } else {
            System.out.println(this.nome + " decide manter sua arma atual.");
        }
    }
    public int getNivel() { return nivel; }
    public double getSorte() { return sorte; }
    
}