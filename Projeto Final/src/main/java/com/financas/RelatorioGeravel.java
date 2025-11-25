package com.financas;

import java.time.LocalDate;
import java.util.List;

/**
 * Interface que define o contrato para geração de relatórios.
 * Qualquer classe que implemente esta interface deve ser capaz de gerar relatórios financeiros.
 * Esta é uma das três interfaces obrigatórias do projeto.
 */
public interface RelatorioGeravel {
    
    /**
     * Gera um relatório de todas as transações em um período específico.
     * 
     * @param dataInicio Data inicial do período
     * @param dataFim Data final do período
     * @return String contendo o relatório formatado
     */
    String gerarRelatorioPorPeriodo(LocalDate dataInicio, LocalDate dataFim);
    
    /**
     * Gera um relatório de transações agrupadas por categoria.
     * 
     * @param dataInicio Data inicial do período
     * @param dataFim Data final do período
     * @return String contendo o relatório formatado
     */
    String gerarRelatorioPorCategoria(LocalDate dataInicio, LocalDate dataFim);
}
