package com.financas;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.util.List;

/**
 * Interface Gráfica do Gerenciador de Finanças Pessoais.
 * Desenvolvida com Swing para proporcionar uma experiência amigável ao usuário.
 * Atende ao requisito de Interface Gráfica do projeto.
 */
public class GUI extends JFrame {
    
    private GerenciadorFinancas gerenciador;
    private JTabbedPane abas;
    private JPanel painelContas, painelCartoes, painelTransacoes, painelRelatorios;
    
    /**
     * Construtor da classe GUI.
     * 
     * @param gerenciador Instância do gerenciador de finanças
     */
    public GUI(GerenciadorFinancas gerenciador) {
        this.gerenciador = gerenciador;
        
        // Configurações da janela
        setTitle("Gerenciador de Finanças Pessoais");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 700);
        setLocationRelativeTo(null);
        setResizable(true);
        
        // Cria as abas
        abas = new JTabbedPane();
        
        painelContas = criarPainelContas();
        painelCartoes = criarPainelCartoes();
        painelTransacoes = criarPainelTransacoes();
        painelRelatorios = criarPainelRelatorios();
        
        abas.addTab("Contas", painelContas);
        abas.addTab("Cartões", painelCartoes);
        abas.addTab("Transações", painelTransacoes);
        abas.addTab("Relatórios", painelRelatorios);
        
        add(abas);
        
        // Menu
        criarMenu();
        
        // Listener para salvar ao fechar
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                gerenciador.salvar();
                System.exit(0);
            }
        });
        
        atualizarAbas();
        
        setVisible(true);
    }
    
    /**
     * Cria o menu da aplicação.
     */
    private void criarMenu() {
        JMenuBar menuBar = new JMenuBar();
        
        JMenu menuArquivo = new JMenu("Arquivo");
        JMenuItem itemSalvar = new JMenuItem("Salvar");
        JMenuItem itemCarregar = new JMenuItem("Carregar");
        JMenuItem itemExportar = new JMenuItem("Exportar para CSV");
        JMenuItem itemSair = new JMenuItem("Sair");
        
        itemSalvar.addActionListener(e -> {
            gerenciador.salvar();
            JOptionPane.showMessageDialog(this, "Dados salvos com sucesso!");
        });
        
        itemCarregar.addActionListener(e -> {
            gerenciador.carregar();
            atualizarAbas();
            JOptionPane.showMessageDialog(this, "Dados carregados com sucesso!");
        });
        
        itemExportar.addActionListener(e -> {
            String nomeArquivo = JOptionPane.showInputDialog(this, "Nome do arquivo CSV:", "transacoes.csv");
            if (nomeArquivo != null && !nomeArquivo.isEmpty()) {
                gerenciador.exportarParaCSV(nomeArquivo);
                JOptionPane.showMessageDialog(this, "Dados exportados com sucesso!");
            }
        });
        
        itemSair.addActionListener(e -> {
            gerenciador.salvar();
            System.exit(0);
        });
        
        menuArquivo.add(itemSalvar);
        menuArquivo.add(itemCarregar);
        menuArquivo.addSeparator();
        menuArquivo.add(itemExportar);
        menuArquivo.addSeparator();
        menuArquivo.add(itemSair);
        
        JMenu menuAjuda = new JMenu("Ajuda");
        JMenuItem itemSobre = new JMenuItem("Sobre");
        itemSobre.addActionListener(e -> JOptionPane.showMessageDialog(this,
            "Gerenciador de Finanças Pessoais v1.0\n" +
            "Projeto Final - MC322A\n" +
            "Programação Orientada a Objetos"));
        menuAjuda.add(itemSobre);
        
        menuBar.add(menuArquivo);
        menuBar.add(menuAjuda);
        setJMenuBar(menuBar);
    }
    
    /**
     * Cria o painel de gerenciamento de contas.
     * 
     * @return JPanel com controles de contas
     */
    private JPanel criarPainelContas() {
        JPanel painel = new JPanel(new BorderLayout());
        
        // Painel de botões
        JPanel painelBotoes = new JPanel();
        JButton btnAdicionarConta = new JButton("Adicionar Conta");
        JButton btnRemoverConta = new JButton("Remover Conta");
        JButton btnAtualizarSaldo = new JButton("Atualizar Saldo");
        
        btnAdicionarConta.addActionListener(e -> adicionarConta());
        btnRemoverConta.addActionListener(e -> removerConta());
        btnAtualizarSaldo.addActionListener(e -> atualizarPainelContas());
        
        painelBotoes.add(btnAdicionarConta);
        painelBotoes.add(btnRemoverConta);
        painelBotoes.add(btnAtualizarSaldo);
        
        // Tabela de contas
        DefaultTableModel modeloTabela = new DefaultTableModel(
            new String[]{"ID", "Tipo", "Nome", "Saldo"}, 0);
        JTable tabelaContas = new JTable(modeloTabela);
        JScrollPane scrollPane = new JScrollPane(tabelaContas);
        
        // Label de saldo total
        JLabel labelSaldoTotal = new JLabel("Saldo Total: R$ 0.00");
        labelSaldoTotal.setFont(new Font("Arial", Font.BOLD, 14));
        
        painel.add(painelBotoes, BorderLayout.NORTH);
        painel.add(scrollPane, BorderLayout.CENTER);
        painel.add(labelSaldoTotal, BorderLayout.SOUTH);
        
        // Armazena referências para atualização
        painel.putClientProperty("tabela", tabelaContas);
        painel.putClientProperty("label", labelSaldoTotal);
        
        return painel;
    }
    
    /**
     * Atualiza o painel de contas.
     */
    private void atualizarPainelContas() {
        JTable tabela = (JTable) painelContas.getClientProperty("tabela");
        JLabel label = (JLabel) painelContas.getClientProperty("label");
        
        DefaultTableModel modelo = (DefaultTableModel) tabela.getModel();
        modelo.setRowCount(0);
        
        for (Conta conta : gerenciador.getContas()) {
            modelo.addRow(new Object[]{
                conta.getId(),
                conta.getTipo(),
                conta.getNome(),
                String.format("R$ %.2f", conta.getSaldo())
            });
        }
        
        label.setText(String.format("Saldo Total: R$ %.2f", gerenciador.calcularSaldoTotal()));
    }
    
    /**
     * Abre diálogo para adicionar uma nova conta.
     */
    private void adicionarConta() {
        JPanel painel = new JPanel(new GridLayout(3, 2));
        
        JLabel labelNome = new JLabel("Nome da Conta:");
        JTextField textNome = new JTextField();
        
        JLabel labelTipo = new JLabel("Tipo:");
        JComboBox<String> comboTipo = new JComboBox<>(new String[]{"Conta Corrente", "Poupança"});
        
        JLabel labelSaldo = new JLabel("Saldo Inicial:");
        JTextField textSaldo = new JTextField();
        
        painel.add(labelNome);
        painel.add(textNome);
        painel.add(labelTipo);
        painel.add(comboTipo);
        painel.add(labelSaldo);
        painel.add(textSaldo);
        
        int resultado = JOptionPane.showConfirmDialog(this, painel, "Adicionar Conta",
            JOptionPane.OK_CANCEL_OPTION);
        
        if (resultado == JOptionPane.OK_OPTION) {
            try {
                String nome = textNome.getText();
                String tipo = (String) comboTipo.getSelectedItem();
                double saldo = Double.parseDouble(textSaldo.getText());
                
                Conta conta;
                if (tipo.equals("Conta Corrente")) {
                    conta = new ContaCorrente(nome, saldo);
                } else {
                    conta = new Poupanca(nome, saldo);
                }
                
                gerenciador.adicionarConta(conta);
                atualizarPainelContas();
                JOptionPane.showMessageDialog(this, "Conta adicionada com sucesso!");
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Erro: Saldo deve ser um número válido!");
            }
        }
    }
    
    /**
     * Remove uma conta selecionada.
     */
    private void removerConta() {
        JTable tabela = (JTable) painelContas.getClientProperty("tabela");
        int linhasSelecionadas = tabela.getSelectedRow();
        
        if (linhasSelecionadas == -1) {
            JOptionPane.showMessageDialog(this, "Selecione uma conta para remover!");
            return;
        }
        
        int id = (int) tabela.getValueAt(linhasSelecionadas, 0);
        Conta conta = gerenciador.obterContaPorId(id);
        
        if (conta != null) {
            int confirmacao = JOptionPane.showConfirmDialog(this,
                "Tem certeza que deseja remover a conta '" + conta.getNome() + "'?",
                "Confirmar Remoção", JOptionPane.YES_NO_OPTION);
            
            if (confirmacao == JOptionPane.YES_OPTION) {
                gerenciador.removerConta(conta);
                atualizarPainelContas();
            }
        }
    }
    
    /**
     * Cria o painel de gerenciamento de cartões de crédito.
     * 
     * @return JPanel com controles de cartões
     */
    private JPanel criarPainelCartoes() {
        JPanel painel = new JPanel(new BorderLayout());
        
        // Painel de botões
        JPanel painelBotoes = new JPanel();
        JButton btnAdicionarCartao = new JButton("Adicionar Cartão");
        JButton btnRemoverCartao = new JButton("Remover Cartão");
        JButton btnRegistrarCompra = new JButton("Registrar Compra");
        JButton btnPagarFatura = new JButton("Pagar Fatura");
        JButton btnAtualizarCartoes = new JButton("Atualizar");
        
        btnAdicionarCartao.addActionListener(e -> adicionarCartao());
        btnRemoverCartao.addActionListener(e -> removerCartao());
        btnRegistrarCompra.addActionListener(e -> registrarCompraCartao());
        btnPagarFatura.addActionListener(e -> pagarFaturaCartao());
        btnAtualizarCartoes.addActionListener(e -> atualizarPainelCartoes());
        
        painelBotoes.add(btnAdicionarCartao);
        painelBotoes.add(btnRemoverCartao);
        painelBotoes.add(btnRegistrarCompra);
        painelBotoes.add(btnPagarFatura);
        painelBotoes.add(btnAtualizarCartoes);
        
        // Tabela de cartões
        DefaultTableModel modeloTabela = new DefaultTableModel(
            new String[]{"ID", "Nome", "Limite", "Disponível", "Fatura", "Fecha", "Vence"}, 0);
        JTable tabelaCartoes = new JTable(modeloTabela);
        JScrollPane scrollPane = new JScrollPane(tabelaCartoes);
        
        painel.add(painelBotoes, BorderLayout.NORTH);
        painel.add(scrollPane, BorderLayout.CENTER);
        
        painel.putClientProperty("tabela", tabelaCartoes);
        
        
        return painel;
    }
    
    /**
     * Atualiza o painel de cartões.
     */
    private void atualizarPainelCartoes() {
        JTable tabela = (JTable) painelCartoes.getClientProperty("tabela");
        DefaultTableModel modelo = (DefaultTableModel) tabela.getModel();
        modelo.setRowCount(0);
        
        for (CartaoCredito cartao : gerenciador.getCartoes()) {
            modelo.addRow(new Object[]{
                cartao.getId(),
                cartao.getNome(),
                String.format("R$ %.2f", cartao.getLimite()),
                String.format("R$ %.2f", cartao.getLimiteDisponivel()),
                String.format("R$ %.2f", cartao.getFaturaAtual()),
                cartao.getDiaFechamento(),
                cartao.getDiaVencimento()
            });
        }
    }
    
    /**
     * Abre diálogo para adicionar um novo cartão.
     */
    private void adicionarCartao() {
        JPanel painel = new JPanel(new GridLayout(5, 2));
        
        JLabel labelNome = new JLabel("Nome do Cartão:");
        JTextField textNome = new JTextField();
        
        JLabel labelLimite = new JLabel("Limite:");
        JTextField textLimite = new JTextField();
        
        JLabel labelFechamento = new JLabel("Dia de Fechamento (1-31):");
        JTextField textFechamento = new JTextField();
        
        JLabel labelVencimento = new JLabel("Dia de Vencimento (1-31):");
        JTextField textVencimento = new JTextField();
        
        painel.add(labelNome);
        painel.add(textNome);
        painel.add(labelLimite);
        painel.add(textLimite);
        painel.add(labelFechamento);
        painel.add(textFechamento);
        painel.add(labelVencimento);
        painel.add(textVencimento);
        
        int resultado = JOptionPane.showConfirmDialog(this, painel, "Adicionar Cartão",
            JOptionPane.OK_CANCEL_OPTION);
        
        if (resultado == JOptionPane.OK_OPTION) {
            try {
                String nome = textNome.getText();
                double limite = Double.parseDouble(textLimite.getText());
                int fechamento = Integer.parseInt(textFechamento.getText());
                int vencimento = Integer.parseInt(textVencimento.getText());
                
                CartaoCredito cartao = new CartaoCredito(nome, limite, fechamento, vencimento);
                gerenciador.adicionarCartao(cartao);
                atualizarPainelCartoes();
                JOptionPane.showMessageDialog(this, "Cartão adicionado com sucesso!");
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Erro: Valores inválidos!");
            } catch (DataInvalidaException e) {
                JOptionPane.showMessageDialog(this, "Erro: " + e.getMessage());
            }
        }
    }
    
    /**
     * Remove um cartão selecionado.
     */
    private void removerCartao() {
        JTable tabela = (JTable) painelCartoes.getClientProperty("tabela");
        int linhasSelecionadas = tabela.getSelectedRow();
        
        if (linhasSelecionadas == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um cartão para remover!");
            return;
        }
        
        int id = (int) tabela.getValueAt(linhasSelecionadas, 0);
        CartaoCredito cartao = gerenciador.obterCartaoPorId(id);
        
        if (cartao != null) {
            int confirmacao = JOptionPane.showConfirmDialog(this,
                "Tem certeza que deseja remover o cartão '" + cartao.getNome() + "'?",
                "Confirmar Remoção", JOptionPane.YES_NO_OPTION);
            
            if (confirmacao == JOptionPane.YES_OPTION) {
                gerenciador.removerCartao(cartao);
                atualizarPainelCartoes();
            }
        }
    }
    
    /**
     * Abre diálogo para registrar uma compra no cartão.
     */
    private void registrarCompraCartao() {
        List<CartaoCredito> cartoes = gerenciador.getCartoes();
        if (cartoes.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nenhum cartão cadastrado!");
            return;
        }
        
        JPanel painel = new JPanel(new GridLayout(5, 2));
        
        JLabel labelCartao = new JLabel("Cartão:");
        JComboBox<CartaoCredito> comboCartao = new JComboBox<>(cartoes.toArray(new CartaoCredito[0]));
        
        JLabel labelValor = new JLabel("Valor:");
        JTextField textValor = new JTextField();
        
        JLabel labelDescricao = new JLabel("Descrição:");
        JTextField textDescricao = new JTextField();
        
        JLabel labelCategoria = new JLabel("Categoria:");
        JComboBox<Categoria> comboCategoria = new JComboBox<>(
            gerenciador.obterCategoriasPorTipo(Categoria.TipoCategoria.DESPESA).toArray(new Categoria[0]));
        
        painel.add(labelCartao);
        painel.add(comboCartao);
        painel.add(labelValor);
        painel.add(textValor);
        painel.add(labelDescricao);
        painel.add(textDescricao);
        painel.add(labelCategoria);
        painel.add(comboCategoria);
        
        int resultado = JOptionPane.showConfirmDialog(this, painel, "Registrar Compra",
            JOptionPane.OK_CANCEL_OPTION);
        
        if (resultado == JOptionPane.OK_OPTION) {
            try {
                CartaoCredito cartao = (CartaoCredito) comboCartao.getSelectedItem();
                double valor = Double.parseDouble(textValor.getText());
                String descricao = textDescricao.getText();
                Categoria categoria = (Categoria) comboCategoria.getSelectedItem();
                
                CompraCartao compra = new CompraCartao(valor, LocalDate.now(), descricao, categoria, cartao.getId());
                gerenciador.registrarCompraCartao(compra);
                atualizarPainelCartoes();
                JOptionPane.showMessageDialog(this, "Compra registrada com sucesso!");
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Erro: Valor inválido!");
            } catch (SaldoInsuficienteException e) {
                JOptionPane.showMessageDialog(this, "Erro: " + e.getMessage());
            }
        }
    }
    
    /**
     * Abre diálogo para pagar a fatura de um cartão.
     */
    private void pagarFaturaCartao() {
        List<CartaoCredito> cartoes = gerenciador.getCartoes();
        if (cartoes.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nenhum cartão cadastrado!");
            return;
        }
        
        JPanel painel = new JPanel(new GridLayout(2, 2));
        
        JLabel labelCartao = new JLabel("Cartão:");
        JComboBox<CartaoCredito> comboCartao = new JComboBox<>(cartoes.toArray(new CartaoCredito[0]));
        
        JLabel labelValor = new JLabel("Valor a Pagar:");
        JTextField textValor = new JTextField();
        
        painel.add(labelCartao);
        painel.add(comboCartao);
        painel.add(labelValor);
        painel.add(textValor);
        
        int resultado = JOptionPane.showConfirmDialog(this, painel, "Pagar Fatura",
            JOptionPane.OK_CANCEL_OPTION);
        
        if (resultado == JOptionPane.OK_OPTION) {
            try {
                CartaoCredito cartao = (CartaoCredito) comboCartao.getSelectedItem();
                double valor = Double.parseDouble(textValor.getText());
                
                cartao.pagarFatura(valor);
                atualizarPainelCartoes();
                JOptionPane.showMessageDialog(this, "Fatura paga com sucesso!");
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Erro: Valor inválido!");
            } catch (SaldoInsuficienteException e) {
                JOptionPane.showMessageDialog(this, "Erro: " + e.getMessage());
            }
        }
    }
    
    /**
     * Cria o painel de gerenciamento de transações.
     * 
     * @return JPanel com controles de transações
     */
    private JPanel criarPainelTransacoes() {
        JPanel painel = new JPanel(new BorderLayout());
        
        // Painel de botões
        JPanel painelBotoes = new JPanel();
        JButton btnAdicionarReceita = new JButton("Adicionar Receita");
        JButton btnAdicionarDespesa = new JButton("Adicionar Despesa");
        JButton btnTransferir = new JButton("Transferir");
        JButton btnRemover = new JButton("Remover");
        JButton btnAtualizar = new JButton("Atualizar");
        
        btnAdicionarReceita.addActionListener(e -> adicionarReceita());
        btnAdicionarDespesa.addActionListener(e -> adicionarDespesa());
        btnTransferir.addActionListener(e -> transferirFundos());
        btnRemover.addActionListener(e -> removerTransacao());
        btnAtualizar.addActionListener(e -> atualizarPainelTransacoes());
        
        painelBotoes.add(btnAdicionarReceita);
        painelBotoes.add(btnAdicionarDespesa);
        painelBotoes.add(btnTransferir);
        painelBotoes.add(btnRemover);
        painelBotoes.add(btnAtualizar);
        
        // Tabela de transações
        DefaultTableModel modeloTabela = new DefaultTableModel(
            new String[]{"ID", "Tipo", "Data", "Descrição", "Valor", "Categoria"}, 0);
        JTable tabelaTransacoes = new JTable(modeloTabela);
        JScrollPane scrollPane = new JScrollPane(tabelaTransacoes);
        
        painel.add(painelBotoes, BorderLayout.NORTH);
        painel.add(scrollPane, BorderLayout.CENTER);
        
        painel.putClientProperty("tabela", tabelaTransacoes);
        
        
        return painel;
    }
    
    /**
     * Atualiza o painel de transações.
     */
    private void atualizarPainelTransacoes() {
        JTable tabela = (JTable) painelTransacoes.getClientProperty("tabela");
        DefaultTableModel modelo = (DefaultTableModel) tabela.getModel();
        modelo.setRowCount(0);
        
        for (Transacao transacao : gerenciador.getTransacoes()) {
            modelo.addRow(new Object[]{
                transacao.getId(),
                transacao.getTipo(),
                transacao.getData(),
                transacao.getDescricao(),
                String.format("R$ %.2f", transacao.getValor()),
                transacao.getCategoria().getNome()
            });
        }
    }
    
    /**
     * Abre diálogo para adicionar uma receita.
     */
    private void adicionarReceita() {
        List<Conta> contas = gerenciador.getContas();
        if (contas.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nenhuma conta cadastrada!");
            return;
        }
        
        JPanel painel = new JPanel(new GridLayout(4, 2));
        
        JLabel labelConta = new JLabel("Conta:");
        JComboBox<Conta> comboConta = new JComboBox<>(contas.toArray(new Conta[0]));
        
        JLabel labelValor = new JLabel("Valor:");
        JTextField textValor = new JTextField();
        
        JLabel labelDescricao = new JLabel("Descrição:");
        JTextField textDescricao = new JTextField();
        
        JLabel labelCategoria = new JLabel("Categoria:");
        JComboBox<Categoria> comboCategoria = new JComboBox<>(
            gerenciador.obterCategoriasPorTipo(Categoria.TipoCategoria.RECEITA).toArray(new Categoria[0]));
        
        painel.add(labelConta);
        painel.add(comboConta);
        painel.add(labelValor);
        painel.add(textValor);
        painel.add(labelDescricao);
        painel.add(textDescricao);
        painel.add(labelCategoria);
        painel.add(comboCategoria);
        
        int resultado = JOptionPane.showConfirmDialog(this, painel, "Adicionar Receita",
            JOptionPane.OK_CANCEL_OPTION);
        
        if (resultado == JOptionPane.OK_OPTION) {
            try {
                Conta conta = (Conta) comboConta.getSelectedItem();
                double valor = Double.parseDouble(textValor.getText());
                String descricao = textDescricao.getText();
                Categoria categoria = (Categoria) comboCategoria.getSelectedItem();
                
                Receita receita = new Receita(valor, LocalDate.now(), descricao, categoria);
                gerenciador.registrarTransacao(receita, conta);
                atualizarPainelTransacoes();
                atualizarPainelContas();
                JOptionPane.showMessageDialog(this, "Receita adicionada com sucesso!");
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Erro: Valor inválido!");
            } catch (SaldoInsuficienteException e) {
                JOptionPane.showMessageDialog(this, "Erro: " + e.getMessage());
            }
        }
    }
    
    /**
     * Abre diálogo para adicionar uma despesa.
     */
    private void adicionarDespesa() {
        List<Conta> contas = gerenciador.getContas();
        if (contas.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nenhuma conta cadastrada!");
            return;
        }
        
        JPanel painel = new JPanel(new GridLayout(4, 2));
        
        JLabel labelConta = new JLabel("Conta:");
        JComboBox<Conta> comboConta = new JComboBox<>(contas.toArray(new Conta[0]));
        
        JLabel labelValor = new JLabel("Valor:");
        JTextField textValor = new JTextField();
        
        JLabel labelDescricao = new JLabel("Descrição:");
        JTextField textDescricao = new JTextField();
        
        JLabel labelCategoria = new JLabel("Categoria:");
        JComboBox<Categoria> comboCategoria = new JComboBox<>(
            gerenciador.obterCategoriasPorTipo(Categoria.TipoCategoria.DESPESA).toArray(new Categoria[0]));
        
        painel.add(labelConta);
        painel.add(comboConta);
        painel.add(labelValor);
        painel.add(textValor);
        painel.add(labelDescricao);
        painel.add(textDescricao);
        painel.add(labelCategoria);
        painel.add(comboCategoria);
        
        int resultado = JOptionPane.showConfirmDialog(this, painel, "Adicionar Despesa",
            JOptionPane.OK_CANCEL_OPTION);
        
        if (resultado == JOptionPane.OK_OPTION) {
            try {
                Conta conta = (Conta) comboConta.getSelectedItem();
                double valor = Double.parseDouble(textValor.getText());
                String descricao = textDescricao.getText();
                Categoria categoria = (Categoria) comboCategoria.getSelectedItem();
                
                Despesa despesa = new Despesa(valor, LocalDate.now(), descricao, categoria);
                gerenciador.registrarTransacao(despesa, conta);
                atualizarPainelTransacoes();
                atualizarPainelContas();
                JOptionPane.showMessageDialog(this, "Despesa adicionada com sucesso!");
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Erro: Valor inválido!");
            } catch (SaldoInsuficienteException e) {
                JOptionPane.showMessageDialog(this, "Erro: " + e.getMessage());
            }
        }
    }
    
    /**
     * Abre diálogo para transferir fundos entre contas.
     */
    private void transferirFundos() {
        List<Conta> contas = gerenciador.getContas();
        if (contas.size() < 2) {
            JOptionPane.showMessageDialog(this, "É necessário ter pelo menos 2 contas para transferir!");
            return;
        }
        
        JPanel painel = new JPanel(new GridLayout(3, 2));
        
        JLabel labelOrigem = new JLabel("Conta de Origem:");
        JComboBox<Conta> comboOrigem = new JComboBox<>(contas.toArray(new Conta[0]));
        
        JLabel labelDestino = new JLabel("Conta de Destino:");
        JComboBox<Conta> comboDestino = new JComboBox<>(contas.toArray(new Conta[0]));
        
        JLabel labelValor = new JLabel("Valor:");
        JTextField textValor = new JTextField();
        
        painel.add(labelOrigem);
        painel.add(comboOrigem);
        painel.add(labelDestino);
        painel.add(comboDestino);
        painel.add(labelValor);
        painel.add(textValor);
        
        int resultado = JOptionPane.showConfirmDialog(this, painel, "Transferir Fundos",
            JOptionPane.OK_CANCEL_OPTION);
        
        if (resultado == JOptionPane.OK_OPTION) {
            try {
                Conta origem = (Conta) comboOrigem.getSelectedItem();
                Conta destino = (Conta) comboDestino.getSelectedItem();
                double valor = Double.parseDouble(textValor.getText());
                
                if (origem.getId() == destino.getId()) {
                    JOptionPane.showMessageDialog(this, "Origem e destino devem ser contas diferentes!");
                    return;
                }
                
                gerenciador.transferirFundos(origem, destino, valor);
                atualizarPainelTransacoes();
                atualizarPainelContas();
                JOptionPane.showMessageDialog(this, "Transferência realizada com sucesso!");
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Erro: Valor inválido!");
            } catch (SaldoInsuficienteException e) {
                JOptionPane.showMessageDialog(this, "Erro: " + e.getMessage());
            }
        }
    }
    
    /**
     * Remove uma transação selecionada.
     */
    private void removerTransacao() {
        JTable tabela = (JTable) painelTransacoes.getClientProperty("tabela");
        int linhasSelecionadas = tabela.getSelectedRow();
        
        if (linhasSelecionadas == -1) {
            JOptionPane.showMessageDialog(this, "Selecione uma transação para remover!");
            return;
        }
        
        JOptionPane.showMessageDialog(this, "Funcionalidade de remoção de transação " +
            "requer seleção da conta associada.\nImplemente conforme necessário.");
    }
    
    /**
     * Cria o painel de relatórios.
     * 
     * @return JPanel com controles de relatórios
     */
    private JPanel criarPainelRelatorios() {
        JPanel painel = new JPanel(new BorderLayout());
        
        // Painel de botões
        JPanel painelBotoes = new JPanel();
        JButton btnRelatorioPeriodo = new JButton("Relatório por Período");
        JButton btnRelatorioCategoria = new JButton("Relatório por Categoria");
        JButton btnVerificaVencimentos = new JButton("Verificar Vencimentos");
        
        btnRelatorioPeriodo.addActionListener(e -> exibirRelatorioPeriodo());
        btnRelatorioCategoria.addActionListener(e -> exibirRelatorioCategoria());
        btnVerificaVencimentos.addActionListener(e -> {
            gerenciador.verificarVencimentos();
            JOptionPane.showMessageDialog(this, "Verificação de vencimentos concluída!");
        });
        
        painelBotoes.add(btnRelatorioPeriodo);
        painelBotoes.add(btnRelatorioCategoria);
        painelBotoes.add(btnVerificaVencimentos);
        
        // Área de texto para exibir relatórios
        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane scrollPane = new JScrollPane(textArea);
        
        painel.add(painelBotoes, BorderLayout.NORTH);
        painel.add(scrollPane, BorderLayout.CENTER);
        
        painel.putClientProperty("textArea", textArea);
        
        return painel;
    }
    
    /**
     * Exibe relatório por período.
     */
    private void exibirRelatorioPeriodo() {
        LocalDate hoje = LocalDate.now();
        LocalDate inicio = hoje.minusMonths(1);
        
        String relatorio = gerenciador.gerarRelatorioPorPeriodo(inicio, hoje);
        
        JTextArea textArea = (JTextArea) painelRelatorios.getClientProperty("textArea");
        textArea.setText(relatorio);
    }
    
    /**
     * Exibe relatório por categoria.
     */
    private void exibirRelatorioCategoria() {
        LocalDate hoje = LocalDate.now();
        LocalDate inicio = hoje.minusMonths(1);
        
        String relatorio = gerenciador.gerarRelatorioPorCategoria(inicio, hoje);
        
        JTextArea textArea = (JTextArea) painelRelatorios.getClientProperty("textArea");
        textArea.setText(relatorio);
    }
    
    /**
     * Atualiza todas as abas.
     */
    private void atualizarAbas() {
        atualizarPainelContas();
        atualizarPainelCartoes();
        atualizarPainelTransacoes();
    }
}
