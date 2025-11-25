package com.financas;

import java.io.*;
import java.time.LocalDate;
import java.util.*;

/**
 * Classe principal que gerencia todo o sistema de finanças pessoais.
 * Implementa as três interfaces obrigatórias: Persistivel, RelatorioGeravel e Notificavel.
 * Demonstra composição: contém listas de Contas, CartõesCredito, Transações e Categorias.
 */
public class GerenciadorFinancas implements Persistivel, RelatorioGeravel, Notificavel {
    
    private static final String ARQUIVO_DADOS = "dados_financeiros.dat";
    private static final long serialVersionUID = 1L;
    
    private List<Conta> contas;
    private List<CartaoCredito> cartoes;
    private List<Transacao> transacoes;
    private List<Categoria> categorias;
    private List<String> notificacoes;
    
    /**
     * Construtor da classe GerenciadorFinancas.
     * Inicializa as listas e categorias padrão.
     */
    public GerenciadorFinancas() {
        this.contas = new ArrayList<>();
        this.cartoes = new ArrayList<>();
        this.transacoes = new ArrayList<>();
        this.categorias = new ArrayList<>();
        this.notificacoes = new ArrayList<>();
        
        // Adiciona categorias padrão
        inicializarCategoriasPadrao();
    }
    
    /**
     * Inicializa as categorias padrão do sistema.
     */
    private void inicializarCategoriasPadrao() {
        // Categorias de receita
        categorias.add(new Categoria("Salário", Categoria.TipoCategoria.RECEITA));
        categorias.add(new Categoria("Freelance", Categoria.TipoCategoria.RECEITA));
        categorias.add(new Categoria("Investimento", Categoria.TipoCategoria.RECEITA));
        categorias.add(new Categoria("Outro", Categoria.TipoCategoria.RECEITA));
        
        // Categorias de despesa
        categorias.add(new Categoria("Alimentação", Categoria.TipoCategoria.DESPESA));
        categorias.add(new Categoria("Transporte", Categoria.TipoCategoria.DESPESA));
        categorias.add(new Categoria("Moradia", Categoria.TipoCategoria.DESPESA));
        categorias.add(new Categoria("Saúde", Categoria.TipoCategoria.DESPESA));
        categorias.add(new Categoria("Educação", Categoria.TipoCategoria.DESPESA));
        categorias.add(new Categoria("Lazer", Categoria.TipoCategoria.DESPESA));
        categorias.add(new Categoria("Utilidades", Categoria.TipoCategoria.DESPESA));
        categorias.add(new Categoria("Outro", Categoria.TipoCategoria.DESPESA));
    }
    
    // ==================== Gerenciamento de Contas ====================
    
    /**
     * Adiciona uma nova conta ao sistema.
     * 
     * @param conta A conta a ser adicionada
     */
    public void adicionarConta(Conta conta) {
        contas.add(conta);
        enviarNotificacao("Conta '" + conta.getNome() + "' adicionada com sucesso!");
    }
    
    /**
     * Remove uma conta do sistema.
     * 
     * @param conta A conta a ser removida
     */
    public void removerConta(Conta conta) {
        if (contas.remove(conta)) {
            enviarNotificacao("Conta '" + conta.getNome() + "' removida com sucesso!");
        }
    }
    
    /**
     * Obtém uma conta pelo ID.
     * 
     * @param id ID da conta
     * @return A conta encontrada, ou null se não existir
     */
    public Conta obterContaPorId(int id) {
        for (Conta conta : contas) {
            if (conta.getId() == id) {
                return conta;
            }
        }
        return null;
    }
    
    /**
     * Retorna a lista de todas as contas.
     * 
     * @return Lista de contas
     */
    public List<Conta> getContas() {
        return new ArrayList<>(contas);
    }
    
    /**
     * Calcula o saldo total de todas as contas.
     * 
     * @return Saldo total
     */
    public double calcularSaldoTotal() {
        double total = 0;
        for (Conta conta : contas) {
            total += conta.getSaldo();
        }
        return total;
    }
    
    // ==================== Gerenciamento de Cartões ====================
    
    /**
     * Adiciona um novo cartão de crédito ao sistema.
     * 
     * @param cartao O cartão a ser adicionado
     */
    public void adicionarCartao(CartaoCredito cartao) {
        cartoes.add(cartao);
        enviarNotificacao("Cartão '" + cartao.getNome() + "' adicionado com sucesso!");
    }
    
    /**
     * Remove um cartão do sistema.
     * 
     * @param cartao O cartão a ser removido
     */
    public void removerCartao(CartaoCredito cartao) {
        if (cartoes.remove(cartao)) {
            enviarNotificacao("Cartão '" + cartao.getNome() + "' removido com sucesso!");
        }
    }
    
    /**
     * Obtém um cartão pelo ID.
     * 
     * @param id ID do cartão
     * @return O cartão encontrado, ou null se não existir
     */
    public CartaoCredito obterCartaoPorId(int id) {
        for (CartaoCredito cartao : cartoes) {
            if (cartao.getId() == id) {
                return cartao;
            }
        }
        return null;
    }
    
    /**
     * Retorna a lista de todos os cartões.
     * 
     * @return Lista de cartões
     */
    public List<CartaoCredito> getCartoes() {
        return new ArrayList<>(cartoes);
    }
    
    /**
     * Calcula o total de faturas em aberto de todos os cartões.
     * 
     * @return Total de faturas
     */
    public double calcularTotalFaturas() {
        double total = 0;
        for (CartaoCredito cartao : cartoes) {
            total += cartao.getFaturaAtual();
        }
        return total;
    }
    
    // ==================== Gerenciamento de Transações ====================
    
    /**
     * Registra uma transação (receita ou despesa) em uma conta.
     * 
     * @param transacao A transação a ser registrada
     * @param conta A conta afetada
     * @throws SaldoInsuficienteException Se for uma despesa e não houver saldo
     */
    public void registrarTransacao(Transacao transacao, Conta conta) 
            throws SaldoInsuficienteException {
        
        if (transacao instanceof Receita) {
            conta.depositar(transacao.getValor());
        } else if (transacao instanceof Despesa && !(transacao instanceof CompraCartao)) {
            conta.sacar(transacao.getValor());
        }
        
        transacoes.add(transacao);
        enviarNotificacao("Transação registrada: " + transacao.getTipo() + 
                         " de R$ " + String.format("%.2f", transacao.getValor()));
    }
    
    /**
     * Registra uma compra no cartão de crédito.
     * 
     * @param compra A compra a ser registrada
     * @throws SaldoInsuficienteException Se não houver limite disponível
     */
    public void registrarCompraCartao(CompraCartao compra) throws SaldoInsuficienteException {
        CartaoCredito cartao = obterCartaoPorId(compra.getIdCartao());
        if (cartao != null) {
            cartao.registrarCompra(compra);
            transacoes.add(compra);
            enviarNotificacao("Compra registrada no cartão '" + cartao.getNome() + 
                            "': R$ " + String.format("%.2f", compra.getValor()));
        }
    }
    
    /**
     * Remove uma transação do sistema.
     * 
     * @param transacao A transação a ser removida
     * @param conta A conta afetada (para reverter o saldo)
     */
    public void removerTransacao(Transacao transacao, Conta conta) {
        if (transacoes.remove(transacao)) {
            if (transacao instanceof Receita) {
                try {
                    conta.sacar(transacao.getValor());
                } catch (SaldoInsuficienteException e) {
                    // Não deve acontecer neste contexto
                }
            } else if (transacao instanceof Despesa && !(transacao instanceof CompraCartao)) {
                conta.depositar(transacao.getValor());
            }
            enviarNotificacao("Transação removida com sucesso!");
        }
    }
    
    /**
     * Retorna a lista de todas as transações.
     * 
     * @return Lista de transações
     */
    public List<Transacao> getTransacoes() {
        return new ArrayList<>(transacoes);
    }
    
    /**
     * Transfere fundos entre duas contas.
     * 
     * @param contaOrigem Conta de origem
     * @param contaDestino Conta de destino
     * @param valor Valor a transferir
     * @throws SaldoInsuficienteException Se não houver saldo na conta de origem
     */
    public void transferirFundos(Conta contaOrigem, Conta contaDestino, double valor) 
            throws SaldoInsuficienteException {
        
        contaOrigem.sacar(valor);
        contaDestino.depositar(valor);
        
        Categoria transferencia = categorias.stream()
            .filter(c -> c.getNome().equals("Outro") && c.getTipo() == Categoria.TipoCategoria.DESPESA)
            .findFirst()
            .orElse(categorias.get(0));
        
        Despesa despesa = new Despesa(valor, LocalDate.now(), 
                "Transferência para " + contaDestino.getNome(), transferencia);
        transacoes.add(despesa);
        
        enviarNotificacao("Transferência de R$ " + String.format("%.2f", valor) + 
                        " realizada com sucesso!");
    }
    
    // ==================== Gerenciamento de Categorias ====================
    
    /**
     * Adiciona uma nova categoria ao sistema.
     * 
     * @param categoria A categoria a ser adicionada
     */
    public void adicionarCategoria(Categoria categoria) {
        if (!categorias.contains(categoria)) {
            categorias.add(categoria);
            enviarNotificacao("Categoria '" + categoria.getNome() + "' adicionada!");
        }
    }
    
    /**
     * Remove uma categoria do sistema.
     * 
     * @param categoria A categoria a ser removida
     */
    public void removerCategoria(Categoria categoria) {
        if (categorias.remove(categoria)) {
            enviarNotificacao("Categoria '" + categoria.getNome() + "' removida!");
        }
    }
    
    /**
     * Retorna a lista de todas as categorias.
     * 
     * @return Lista de categorias
     */
    public List<Categoria> getCategorias() {
        return new ArrayList<>(categorias);
    }
    
    /**
     * Obtém categorias de um tipo específico.
     * 
     * @param tipo Tipo de categoria (RECEITA ou DESPESA)
     * @return Lista de categorias do tipo especificado
     */
    public List<Categoria> obterCategoriasPorTipo(Categoria.TipoCategoria tipo) {
        List<Categoria> resultado = new ArrayList<>();
        for (Categoria categoria : categorias) {
            if (categoria.getTipo() == tipo) {
                resultado.add(categoria);
            }
        }
        return resultado;
    }
    
    // ==================== Relatórios (Interface RelatorioGeravel) ====================
    
    @Override
    public String gerarRelatorioPorPeriodo(LocalDate dataInicio, LocalDate dataFim) {
        StringBuilder sb = new StringBuilder();
        sb.append("\n========== RELATÓRIO POR PERÍODO ==========\n");
        sb.append("Período: ").append(dataInicio).append(" a ").append(dataFim).append("\n\n");
        
        double totalReceitas = 0;
        double totalDespesas = 0;
        
        for (Transacao t : transacoes) {
            if (!t.getData().isBefore(dataInicio) && !t.getData().isAfter(dataFim)) {
                sb.append(t).append("\n");
                
                if (t instanceof Receita) {
                    totalReceitas += t.getValor();
                } else if (t instanceof Despesa) {
                    totalDespesas += t.getValor();
                }
            }
        }
        
        sb.append("\n--- RESUMO ---\n");
        sb.append(String.format("Total de Receitas: R$ %.2f\n", totalReceitas));
        sb.append(String.format("Total de Despesas: R$ %.2f\n", totalDespesas));
        sb.append(String.format("Saldo do Período: R$ %.2f\n", totalReceitas - totalDespesas));
        sb.append("==========================================\n");
        
        return sb.toString();
    }
    
    @Override
    public String gerarRelatorioPorCategoria(LocalDate dataInicio, LocalDate dataFim) {
        StringBuilder sb = new StringBuilder();
        sb.append("\n========== RELATÓRIO POR CATEGORIA ==========\n");
        sb.append("Período: ").append(dataInicio).append(" a ").append(dataFim).append("\n\n");
        
        Map<String, Double> categoriasReceita = new HashMap<>();
        Map<String, Double> categoriasDespesa = new HashMap<>();
        
        for (Transacao t : transacoes) {
            if (!t.getData().isBefore(dataInicio) && !t.getData().isAfter(dataFim)) {
                String nomeCategoria = t.getCategoria().getNome();
                
                if (t instanceof Receita) {
                    categoriasReceita.put(nomeCategoria, 
                        categoriasReceita.getOrDefault(nomeCategoria, 0.0) + t.getValor());
                } else if (t instanceof Despesa) {
                    categoriasDespesa.put(nomeCategoria, 
                        categoriasDespesa.getOrDefault(nomeCategoria, 0.0) + t.getValor());
                }
            }
        }
        
        sb.append("--- RECEITAS POR CATEGORIA ---\n");
        for (Map.Entry<String, Double> entry : categoriasReceita.entrySet()) {
            sb.append(String.format("%s: R$ %.2f\n", entry.getKey(), entry.getValue()));
        }
        
        sb.append("\n--- DESPESAS POR CATEGORIA ---\n");
        for (Map.Entry<String, Double> entry : categoriasDespesa.entrySet()) {
            sb.append(String.format("%s: R$ %.2f\n", entry.getKey(), entry.getValue()));
        }
        
        sb.append("=============================================\n");
        
        return sb.toString();
    }
    
    // ==================== Notificações (Interface Notificavel) ====================
    
    @Override
    public void enviarNotificacao(String mensagem) {
        String notificacao = "[" + LocalDate.now() + "] " + mensagem;
        notificacoes.add(notificacao);
        System.out.println("📢 " + notificacao);
    }
    
    /**
     * Retorna todas as notificações.
     * 
     * @return Lista de notificações
     */
    public List<String> getNotificacoes() {
        return new ArrayList<>(notificacoes);
    }
    
    /**
     * Verifica vencimentos de cartões e gera notificações.
     */
    public void verificarVencimentos() {
        LocalDate hoje = LocalDate.now();
        int diaAtual = hoje.getDayOfMonth();
        
        for (CartaoCredito cartao : cartoes) {
            if (diaAtual == cartao.getDiaVencimento()) {
                enviarNotificacao("⚠️ ATENÇÃO: Cartão '" + cartao.getNome() + 
                                "' vence HOJE! Fatura: R$ " + 
                                String.format("%.2f", cartao.getFaturaAtual()));
            } else if (diaAtual == cartao.getDiaFechamento()) {
                enviarNotificacao("📅 Fatura do cartão '" + cartao.getNome() + 
                                "' foi fechada. Valor: R$ " + 
                                String.format("%.2f", cartao.getFaturaAtual()));
            }
        }
    }
    
    // ==================== Persistência (Interface Persistivel) ====================
    
    @Override
    public void salvar() {
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(ARQUIVO_DADOS))) {
            
            oos.writeObject(contas);
            oos.writeObject(cartoes);
            oos.writeObject(transacoes);
            oos.writeObject(categorias);
            
            enviarNotificacao("✅ Dados salvos com sucesso!");
        } catch (IOException e) {
            enviarNotificacao("❌ Erro ao salvar dados: " + e.getMessage());
        }
    }
    
    @Override
    public void carregar() {
        File arquivo = new File(ARQUIVO_DADOS);
        if (!arquivo.exists()) {
            enviarNotificacao("ℹ️ Nenhum arquivo de dados encontrado. Iniciando com dados vazios.");
            return;
        }
        
        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream(ARQUIVO_DADOS))) {
            
            contas = (List<Conta>) ois.readObject();
            cartoes = (List<CartaoCredito>) ois.readObject();
            transacoes = (List<Transacao>) ois.readObject();
            categorias = (List<Categoria>) ois.readObject();
            
            enviarNotificacao("✅ Dados carregados com sucesso!");
        } catch (IOException | ClassNotFoundException e) {
            enviarNotificacao("❌ Erro ao carregar dados: " + e.getMessage());
        }
    }
    
    /**
     * Exporta as transações para um arquivo CSV.
     * 
     * @param nomeArquivo Nome do arquivo CSV
     */
    public void exportarParaCSV(String nomeArquivo) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(nomeArquivo))) {
            writer.println("ID,Tipo,Data,Descrição,Valor,Categoria");
            
            for (Transacao t : transacoes) {
                writer.printf("%d,%s,%s,%s,%.2f,%s\n",
                    t.getId(),
                    t.getTipo(),
                    t.getData(),
                    t.getDescricao(),
                    t.getValor(),
                    t.getCategoria().getNome());
            }
            
            enviarNotificacao("✅ Dados exportados para " + nomeArquivo);
        } catch (IOException e) {
            enviarNotificacao("❌ Erro ao exportar dados: " + e.getMessage());
        }
    }
}
