package com.financas;

import java.io.Serializable;

/**
 * Representa uma categoria de transação (receita ou despesa).
 * Permite a organização e análise de transações por tipo.
 */
public class Categoria implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private String nome;
    private TipoCategoria tipo;
    
    /**
     * Enumeração para os tipos de categoria.
     */
    public enum TipoCategoria {
        RECEITA, DESPESA
    }
    
    /**
     * Construtor da classe Categoria.
     * 
     * @param nome Nome da categoria
     * @param tipo Tipo da categoria (RECEITA ou DESPESA)
     */
    public Categoria(String nome, TipoCategoria tipo) {
        this.nome = nome;
        this.tipo = tipo;
    }
    
    public String getNome() {
        return nome;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public TipoCategoria getTipo() {
        return tipo;
    }
    
    public void setTipo(TipoCategoria tipo) {
        this.tipo = tipo;
    }
    
    @Override
    public String toString() {
        return nome + " (" + tipo + ")";
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Categoria categoria = (Categoria) obj;
        return nome.equals(categoria.nome) && tipo == categoria.tipo;
    }
    
    @Override
    public int hashCode() {
        return nome.hashCode() + tipo.hashCode();
    }
}
