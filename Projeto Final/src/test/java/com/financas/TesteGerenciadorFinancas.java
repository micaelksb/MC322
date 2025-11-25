package com.financas;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.time.LocalDate;

/**
 * Testes unitários para a classe GerenciadorFinancas.
 * Atende ao requisito de testes unitários do projeto.
 */
public class TesteGerenciadorFinancas {
    
    private GerenciadorFinancas gerenciador;
    
    @Before
    public void setUp() {
        gerenciador = new GerenciadorFinancas();
    }
    
    // ==================== Testes de Contas ====================
    
    @Test
    public void testeAdicionarConta() {
        Conta conta = new ContaCorrente("Conta Teste", 1000.0);
        gerenciador.adicionarConta(conta);
        
        assertEquals(1, gerenciador.getContas().size());
        assertEquals("Conta Teste", gerenciador.getContas().get(0).getNome());
    }
    
    @Test
    public void testeRemoverConta() {
        Conta conta = new ContaCorrente("Conta Teste", 1000.0);
        gerenciador.adicionarConta(conta);
        gerenciador.removerConta(conta);
        
        assertEquals(0, gerenciador.getContas().size());
    }
    
    @Test
    public void testeSaldoTotal() {
        gerenciador.adicionarConta(new ContaCorrente("Conta 1", 1000.0));
        gerenciador.adicionarConta(new Poupanca("Conta 2", 2000.0));
        
        assertEquals(3000.0, gerenciador.calcularSaldoTotal(), 0.01);
    }
    
    @Test
    public void testeObterContaPorId() {
        Conta conta = new ContaCorrente("Conta Teste", 1000.0);
        gerenciador.adicionarConta(conta);
        
        Conta contaRecuperada = gerenciador.obterContaPorId(conta.getId());
        assertNotNull(contaRecuperada);
        assertEquals("Conta Teste", contaRecuperada.getNome());
    }
    
    // ==================== Testes de Cartões ====================
    
    @Test
    public void testeAdicionarCartao() throws DataInvalidaException {
        CartaoCredito cartao = new CartaoCredito("Visa", 5000.0, 10, 20);
        gerenciador.adicionarCartao(cartao);
        
        assertEquals(1, gerenciador.getCartoes().size());
        assertEquals("Visa", gerenciador.getCartoes().get(0).getNome());
    }
    
    @Test
    public void testeRemoverCartao() throws DataInvalidaException {
        CartaoCredito cartao = new CartaoCredito("Visa", 5000.0, 10, 20);
        gerenciador.adicionarCartao(cartao);
        gerenciador.removerCartao(cartao);
        
        assertEquals(0, gerenciador.getCartoes().size());
    }
    
    @Test
    public void testeTotalFaturas() throws DataInvalidaException {
        CartaoCredito cartao1 = new CartaoCredito("Visa", 5000.0, 10, 20);
        CartaoCredito cartao2 = new CartaoCredito("Mastercard", 3000.0, 15, 25);
        
        gerenciador.adicionarCartao(cartao1);
        gerenciador.adicionarCartao(cartao2);
        
        assertEquals(0.0, gerenciador.calcularTotalFaturas(), 0.01);
    }
    
    @Test
    public void testeDataInvalidaException() {
        assertThrows(DataInvalidaException.class, () -> {
            new CartaoCredito("Visa", 5000.0, 32, 20);
        });
    }
    
    // ==================== Testes de Transações ====================
    
    @Test
    public void testeAdicionarReceita() throws SaldoInsuficienteException {
        Conta conta = new ContaCorrente("Conta Teste", 1000.0);
        gerenciador.adicionarConta(conta);
        
        Categoria categoria = gerenciador.getCategorias().get(0);
        Receita receita = new Receita(500.0, LocalDate.now(), "Receita Teste", categoria);
        
        gerenciador.registrarTransacao(receita, conta);
        
        assertEquals(1, gerenciador.getTransacoes().size());
        assertEquals(1500.0, conta.getSaldo(), 0.01);
    }
    
    @Test
    public void testeAdicionarDespesa() throws SaldoInsuficienteException {
        Conta conta = new ContaCorrente("Conta Teste", 1000.0);
        gerenciador.adicionarConta(conta);
        
        Categoria categoria = gerenciador.obterCategoriasPorTipo(Categoria.TipoCategoria.DESPESA).get(0);
        Despesa despesa = new Despesa(300.0, LocalDate.now(), "Despesa Teste", categoria);
        
        gerenciador.registrarTransacao(despesa, conta);
        
        assertEquals(1, gerenciador.getTransacoes().size());
        assertEquals(700.0, conta.getSaldo(), 0.01);
    }
    
    @Test
    public void testeSaldoInsuficienteException() {
        Conta conta = new ContaCorrente("Conta Teste", 100.0);
        gerenciador.adicionarConta(conta);
        
        Categoria categoria = gerenciador.obterCategoriasPorTipo(Categoria.TipoCategoria.DESPESA).get(0);
        Despesa despesa = new Despesa(500.0, LocalDate.now(), "Despesa Teste", categoria);
        
        assertThrows(SaldoInsuficienteException.class, () -> {
            gerenciador.registrarTransacao(despesa, conta);
        });
    }
    
    @Test
    public void testeTransferirFundos() throws SaldoInsuficienteException {
        Conta conta1 = new ContaCorrente("Conta 1", 1000.0);
        Conta conta2 = new ContaCorrente("Conta 2", 500.0);
        
        gerenciador.adicionarConta(conta1);
        gerenciador.adicionarConta(conta2);
        
        gerenciador.transferirFundos(conta1, conta2, 200.0);
        
        assertEquals(800.0, conta1.getSaldo(), 0.01);
        assertEquals(700.0, conta2.getSaldo(), 0.01);
    }
    
    // ==================== Testes de Compras no Cartão ====================
    
    @Test
    public void testeRegistrarCompraCartao() throws DataInvalidaException, SaldoInsuficienteException {
        CartaoCredito cartao = new CartaoCredito("Visa", 5000.0, 10, 20);
        gerenciador.adicionarCartao(cartao);
        
        Categoria categoria = gerenciador.obterCategoriasPorTipo(Categoria.TipoCategoria.DESPESA).get(0);
        CompraCartao compra = new CompraCartao(500.0, LocalDate.now(), "Compra Teste", categoria, cartao.getId());
        
        gerenciador.registrarCompraCartao(compra);
        
        assertEquals(1, gerenciador.getTransacoes().size());
        assertEquals(500.0, cartao.getFaturaAtual(), 0.01);
        assertEquals(4500.0, cartao.getLimiteDisponivel(), 0.01);
    }
    
    @Test
    public void testeLimiteInsuficienteCartao() throws DataInvalidaException {
        CartaoCredito cartao = new CartaoCredito("Visa", 1000.0, 10, 20);
        gerenciador.adicionarCartao(cartao);
        
        Categoria categoria = gerenciador.obterCategoriasPorTipo(Categoria.TipoCategoria.DESPESA).get(0);
        CompraCartao compra = new CompraCartao(1500.0, LocalDate.now(), "Compra Teste", categoria, cartao.getId());
        
        assertThrows(SaldoInsuficienteException.class, () -> {
            gerenciador.registrarCompraCartao(compra);
        });
    }
    
    @Test
    public void testePagarFaturaCartao() throws DataInvalidaException, SaldoInsuficienteException {
        CartaoCredito cartao = new CartaoCredito("Visa", 5000.0, 10, 20);
        gerenciador.adicionarCartao(cartao);
        
        Categoria categoria = gerenciador.obterCategoriasPorTipo(Categoria.TipoCategoria.DESPESA).get(0);
        CompraCartao compra = new CompraCartao(500.0, LocalDate.now(), "Compra Teste", categoria, cartao.getId());
        
        gerenciador.registrarCompraCartao(compra);
        cartao.pagarFatura(500.0);
        
        assertEquals(0.0, cartao.getFaturaAtual(), 0.01);
        assertEquals(5000.0, cartao.getLimiteDisponivel(), 0.01);
    }
    
    // ==================== Testes de Categorias ====================
    
    @Test
    public void testeAdicionarCategoria() {
        int tamanhoInicial = gerenciador.getCategorias().size();
        
        Categoria categoria = new Categoria("Categoria Teste", Categoria.TipoCategoria.DESPESA);
        gerenciador.adicionarCategoria(categoria);
        
        assertEquals(tamanhoInicial + 1, gerenciador.getCategorias().size());
    }
    
    @Test
    public void testeObterCategoriasPorTipo() {
        int receitasIniciais = gerenciador.obterCategoriasPorTipo(Categoria.TipoCategoria.RECEITA).size();
        int despesasIniciais = gerenciador.obterCategoriasPorTipo(Categoria.TipoCategoria.DESPESA).size();
        
        assertTrue(receitasIniciais > 0);
        assertTrue(despesasIniciais > 0);
    }
    
    // ==================== Testes de Contas ====================
    
    @Test
    public void testeDepositoConta() throws SaldoInsuficienteException {
        Conta conta = new ContaCorrente("Conta Teste", 1000.0);
        conta.depositar(500.0);
        
        assertEquals(1500.0, conta.getSaldo(), 0.01);
    }
    
    @Test
    public void testeSaqueConta() throws SaldoInsuficienteException {
        Conta conta = new ContaCorrente("Conta Teste", 1000.0);
        conta.sacar(300.0);
        
        assertEquals(700.0, conta.getSaldo(), 0.01);
    }
    
    @Test
    public void testeSaqueInvalidoConta() {
        Conta conta = new ContaCorrente("Conta Teste", 1000.0);
        
        assertThrows(SaldoInsuficienteException.class, () -> {
            conta.sacar(1500.0);
        });
    }
    
    // ==================== Testes de Relatórios ====================
    
    @Test
    public void testeGerarRelatorioPorPeriodo() throws SaldoInsuficienteException {
        Conta conta = new ContaCorrente("Conta Teste", 1000.0);
        gerenciador.adicionarConta(conta);
        
        Categoria categoria = gerenciador.getCategorias().get(0);
        Receita receita = new Receita(500.0, LocalDate.now(), "Receita Teste", categoria);
        gerenciador.registrarTransacao(receita, conta);
        
        String relatorio = gerenciador.gerarRelatorioPorPeriodo(
            LocalDate.now().minusDays(1), LocalDate.now().plusDays(1));
        
        assertTrue(relatorio.contains("RECEITA"));
        assertTrue(relatorio.contains("500.00"));
    }
    
    @Test
    public void testeGerarRelatorioPorCategoria() throws SaldoInsuficienteException {
        Conta conta = new ContaCorrente("Conta Teste", 1000.0);
        gerenciador.adicionarConta(conta);
        
        Categoria categoria = gerenciador.getCategorias().get(0);
        Receita receita = new Receita(500.0, LocalDate.now(), "Receita Teste", categoria);
        gerenciador.registrarTransacao(receita, conta);
        
        String relatorio = gerenciador.gerarRelatorioPorCategoria(
            LocalDate.now().minusDays(1), LocalDate.now().plusDays(1));
        
        assertTrue(relatorio.contains("RECEITAS POR CATEGORIA"));
    }
    
    // ==================== Testes de Notificações ====================
    
    @Test
    public void testeEnviarNotificacao() {
        int tamanhoInicial = gerenciador.getNotificacoes().size();
        
        gerenciador.enviarNotificacao("Teste de notificação");
        
        assertEquals(tamanhoInicial + 1, gerenciador.getNotificacoes().size());
    }
}
