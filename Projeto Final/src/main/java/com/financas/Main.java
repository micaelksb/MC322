package com.financas;

import javax.swing.SwingUtilities;

/**
 * Classe principal que inicia o Gerenciador de Finanças Pessoais.
 * Cria uma instância do gerenciador, carrega dados persistidos e exibe a GUI.
 */
public class Main {
    
    /**
     * Método principal da aplicação.
     * 
     * @param args Argumentos de linha de comando (não utilizados)
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Cria a instância do gerenciador
            GerenciadorFinancas gerenciador = new GerenciadorFinancas();
            
            // Carrega dados persistidos
            gerenciador.carregar();
            
            // Exibe a interface gráfica
            new GUI(gerenciador);
        });
    }
}
