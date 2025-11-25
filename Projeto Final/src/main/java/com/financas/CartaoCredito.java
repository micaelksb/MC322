package com.financas;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Representa um cartão de crédito com limite, data de fechamento e vencimento.
 * Gerencia as compras realizadas com o cartão.
 * Requisito específico do usuário: suporta múltiplos cartões com datas de vencimento.
 */
public class CartaoCredito implements Serializable {
    
    private static final long serialVersionUID = 1L;
    private static int proximoId = 1;
    
    private int id;
    private String nome;
    private double limite;
    private double limiteDisponivel;
    private int diaFechamento;      // Dia do mês em que a fatura fecha
    private int diaVencimento;      // Dia do mês em que a fatura vence
    private List<CompraCartao> compras;
    private double saldoFatura;     // Saldo atual da fatura aberta
    
    /**
     * Construtor da classe CartaoCredito.
     * 
     * @param nome Nome do cartão (Ex: "Visa", "Mastercard")
     * @param limite Limite de crédito do cartão
     * @param diaFechamento Dia do mês em que a fatura fecha (1-31)
     * @param diaVencimento Dia do mês em que a fatura vence (1-31)
     * @throws DataInvalidaException Se os dias forem inválidos
     */
    public CartaoCredito(String nome, double limite, int diaFechamento, int diaVencimento) 
            throws DataInvalidaException {
        if (diaFechamento < 1 || diaFechamento > 31 || diaVencimento < 1 || diaVencimento > 31) {
            throw new DataInvalidaException("Dias de fechamento e vencimento devem estar entre 1 e 31");
        }
        
        this.id = proximoId++;
        this.nome = nome;
        this.limite = limite;
        this.limiteDisponivel = limite;
        this.diaFechamento = diaFechamento;
        this.diaVencimento = diaVencimento;
        this.compras = new ArrayList<>();
        this.saldoFatura = 0;
    }
    
    public int getId() {
        return id;
    }
    
    public String getNome() {
        return nome;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public double getLimite() {
        return limite;
    }
    
    public void setLimite(double limite) {
        this.limite = limite;
    }
    
    public double getLimiteDisponivel() {
        return limiteDisponivel;
    }
    
    public int getDiaFechamento() {
        return diaFechamento;
    }
    
    public void setDiaFechamento(int diaFechamento) throws DataInvalidaException {
        if (diaFechamento < 1 || diaFechamento > 31) {
            throw new DataInvalidaException("Dia de fechamento deve estar entre 1 e 31");
        }
        this.diaFechamento = diaFechamento;
    }
    
    public int getDiaVencimento() {
        return diaVencimento;
    }
    
    public void setDiaVencimento(int diaVencimento) throws DataInvalidaException {
        if (diaVencimento < 1 || diaVencimento > 31) {
            throw new DataInvalidaException("Dia de vencimento deve estar entre 1 e 31");
        }
        this.diaVencimento = diaVencimento;
    }
    
    public List<CompraCartao> getCompras() {
        return new ArrayList<>(compras);
    }
    
    public double getSaldoFatura() {
        return saldoFatura;
    }
    
    /**
     * Registra uma compra no cartão.
     * 
     * @param compra A compra a ser registrada
     * @throws SaldoInsuficienteException Se não houver limite disponível
     */
    public void registrarCompra(CompraCartao compra) throws SaldoInsuficienteException {
        if (compra.getValor() > limiteDisponivel) {
            throw new SaldoInsuficienteException(
                String.format("Limite insuficiente. Limite disponível: R$ %.2f, Valor da compra: R$ %.2f",
                    limiteDisponivel, compra.getValor())
            );
        }
        
        compras.add(compra);
        limiteDisponivel -= compra.getValor();
        saldoFatura += compra.getValor();
    }
    
    /**
     * Retorna a fatura atual (compras não pagas).
     * 
     * @return Valor total da fatura atual
     */
    public double getFaturaAtual() {
        return saldoFatura;
    }
    
    /**
     * Paga uma parte ou toda a fatura.
     * 
     * @param valor Valor a pagar
     * @throws SaldoInsuficienteException Se o valor for maior que a fatura
     */
    public void pagarFatura(double valor) throws SaldoInsuficienteException {
        if (valor > saldoFatura) {
            throw new SaldoInsuficienteException(
                String.format("Valor de pagamento maior que a fatura. Fatura: R$ %.2f, Pagamento: R$ %.2f",
                    saldoFatura, valor)
            );
        }
        
        saldoFatura -= valor;
        limiteDisponivel += valor;
        
        // Marca compras como pagas (de forma simplificada)
        double restante = valor;
        for (CompraCartao compra : compras) {
            if (!compra.isFaturaPaga() && restante > 0) {
                if (restante >= compra.getValor()) {
                    compra.setFaturaPaga(true);
                    restante -= compra.getValor();
                } else {
                    restante = 0;
                }
            }
        }
    }
    
    /**
     * Remove uma compra do cartão (devolução).
     * 
     * @param compra A compra a ser removida
     */
    public void removerCompra(CompraCartao compra) {
        if (compras.remove(compra)) {
            limiteDisponivel += compra.getValor();
            if (!compra.isFaturaPaga()) {
                saldoFatura -= compra.getValor();
            }
        }
    }
    
    /**
     * Obtém as compras não pagas da fatura atual.
     * 
     * @return Lista de compras não pagas
     */
    public List<CompraCartao> getComprasNaoPagas() {
        List<CompraCartao> naoPagas = new ArrayList<>();
        for (CompraCartao compra : compras) {
            if (!compra.isFaturaPaga()) {
                naoPagas.add(compra);
            }
        }
        return naoPagas;
    }
    
    @Override
    public String toString() {
        return String.format("[%d] %s - Limite: R$ %.2f, Disponível: R$ %.2f, Fatura: R$ %.2f " +
                "(Fecha: %d, Vence: %d)",
                id, nome, limite, limiteDisponivel, saldoFatura, diaFechamento, diaVencimento);
    }
}
