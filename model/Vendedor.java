package model;

import java.math.BigDecimal;

public class Vendedor {
	
	private int id = 0;
	private String nome;
    private String cpf;
    private String sexo;
    private BigDecimal salario;
    private int comissao;
    private int anoContratacao;
    private String observacoes;
   
    
	public Vendedor(String nome, String cpf, String sexo, BigDecimal salario,
			int anoContratacao, String observacoes) {

		this.nome = nome;
		this.cpf = cpf;
		this.sexo = sexo;
		this.salario = salario;
		this.anoContratacao = anoContratacao;
		this.observacoes = observacoes;
	}
	
	@Override
	public String toString() {
		return getNome();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public BigDecimal getSalario() {
		return salario;
	}

	public void setSalario(BigDecimal salario) {
		this.salario = salario;
	}

	public int getComissao() {
		return comissao;
	}

	public void setComissao(int comissao) {
		this.comissao = comissao;
	}

	public String getObservacoes() {
		return observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}

	public int getAnoContratacao() {
		return anoContratacao;
	}
	public void setAnoContratacao(int anoContratacao) {
		this.anoContratacao = anoContratacao;
	}
}
