package com.rpg.personagens;

import com.rpg.combate.Combatente;
import com.rpg.combate.AcaoDeCombate;
import com.rpg.itens.Arma;
import java.util.List;

public abstract class Personagem implements Combatente {
    protected String nome;
    protected int pontosDeVida;
    protected int forca;
    protected Arma arma;
    
    protected List<AcaoDeCombate> acoes;

    public Personagem(String nome, int pontosDeVida, int forca) {
        this.nome = nome;
        this.pontosDeVida = pontosDeVida;
        this.forca = forca;
        this.arma = null;
    }

    // metodos da interface combatente

    @Override
    public String getNome() {
        return this.nome;
    }

    @Override
    public boolean estaVivo() {
        return this.pontosDeVida > 0;
    }
    
    /**
     * Aumenta os pontos de vida do personagem e exibe uma mensagem no console.
     * @param cura A quantidade de pontos de vida a serem restaurados.
     */
    @Override
    public void receberCura(int cura) {
        this.pontosDeVida += cura;
        System.out.println(this.nome + " recebeu " + cura + " de cura. Vida atual: " + this.pontosDeVida);
    }
    
    /**
     * Reduz os pontos de vida do personagem e garante que a vida não fique negativa.
     * Exibe uma mensagem sobre o dano recebido.
     * @param dano A quantidade de dano a ser aplicada.
     */
    @Override
    public void receberDano(int dano) {
        this.pontosDeVida -= dano;
        if (this.pontosDeVida < 0) {
            this.pontosDeVida = 0;
        }
        System.out.println(this.nome + " recebeu " + dano + " de dano. Vida restante: " + this.pontosDeVida);
    }
    
    @Override
    public abstract AcaoDeCombate escolherAcao(Combatente alvo);

    public void exibirStatus() {
        System.out.println("\n--- Status de " + this.nome + " ---");
        System.out.println("Pontos de Vida: " + this.pontosDeVida);
        System.out.println("Força: " + this.forca);
        if (arma != null) {
            System.out.println("Arma: " + arma.getNome() + " (Dano: " + arma.getDano() + ")");
        } else {
            System.out.println("Arma: Nenhuma");
        }
    }
    
    public abstract int calcularDanoBase();

    // Getters e Setters
    public int getPontosDeVida() { return pontosDeVida; }
    public int getForca() { return forca; }
    public Arma getArma() { return arma; }
    public void setArma(Arma arma) { this.arma = arma; }
}