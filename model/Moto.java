package model;

import java.math.BigDecimal;

public class Moto {
	
	private int id = 0;
	private String marca;
    private String modelo;
    private String cor;
    private int cilindrada;
    private int ano;
    private BigDecimal preco;
    private int quantidade;
    private String observacoes;
    
	public Moto(String marca, String modelo, String cor,int cilindrada, int ano,BigDecimal preco,
			int quantidade, String observacoes) {

		this.marca = marca;
		this.modelo = modelo;
		this.cor = cor;
		this.cilindrada = cilindrada;
		this.ano = ano;
		this.preco = preco;
		this.quantidade = quantidade;
		this.observacoes = observacoes;
	}
	
	@Override
	public String toString() {
		String[] marcaModelo = {marca, modelo};
		return getMarca()+ " " + getModelo();
	}
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public String getMarca() {
		return marca;
	}


	public void setMarca(String marca) {
		this.marca = marca;
	}


	public String getModelo() {
		return modelo;
	}


	public void setModelo(String modelo) {
		this.modelo = modelo;
	}


	public String getCor() {
		return cor;
	}


	public void setCor(String cor) {
		this.cor = cor;
	}


	public int getCilindrada() {
		return cilindrada;
	}


	public void setCilindrada(int cilindrada) {
		this.cilindrada = cilindrada;
	}
	
	public int getAno() {
		return ano;
	}
	
	public void setAno(int ano) {
		this.ano = ano;
	}

	public BigDecimal getPreco() {
		return preco;
	}


	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}
	
	


	public int getQuantidade() {
		return quantidade;
	}


	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}


	public String getObservacoes() {
		return observacoes;
	}


	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}

	

	
}
