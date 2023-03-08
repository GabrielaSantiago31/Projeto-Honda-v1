package model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Venda {
	private int id;
	private Vendedor vendedor;
	private Moto moto;
	private BigDecimal motoValor;
	private int qtdMotosVendidas;
	private BigDecimal totVenda;
	private String formaPagamento;
	private LocalDateTime dataHora;
	
	public Venda(Vendedor vendedor, Moto moto, int qtdMotosVendidas,
			BigDecimal totVenda, String pagamento, LocalDateTime dataHora) {
		super();
		this.vendedor = vendedor;
		this.moto = moto;
		this.qtdMotosVendidas = qtdMotosVendidas;
		this.totVenda = totVenda;
		this.formaPagamento = pagamento;
		this.dataHora = dataHora;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Vendedor getVendedor() {
		return vendedor;
	}

	public void setVendedor(Vendedor vendedor) {
		this.vendedor = vendedor;
	}

	public Moto getMoto() {
		return moto;
	}

	public void setMoto(Moto moto) {
		this.moto = moto;
	}

	public BigDecimal getMotoValor() {
		return motoValor;
	}

	public void setMotoValor(BigDecimal motoValor) {
		this.motoValor = motoValor;
	}

	public int getQtdMotosVendidas() {
		return qtdMotosVendidas;
	}

	public void setQtdMotosVendidas(int qtdMotosVendidas) {
		this.qtdMotosVendidas = qtdMotosVendidas;
	}

	public BigDecimal getTotVenda() {
		return totVenda;
	}

	public void setTotVenda(BigDecimal totVenda) {
		this.totVenda = totVenda;
	}

	public String getFormaPagamento() {
		return formaPagamento;
	}

	public void setFormasPagamento(String formaPagamento) {
		this.formaPagamento = formaPagamento;
	}

	public void setFormaPagamento(String formaPagamento) {
		this.formaPagamento = formaPagamento;
	}

	public LocalDateTime getDataHora() {
		return dataHora;
	}

	public void setDataHora(LocalDateTime dataHora) {
		this.dataHora = dataHora;
	}
	
	
	
}
