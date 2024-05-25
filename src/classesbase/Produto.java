package classesbase;

import util.ConstantesControleEstoque;

/**
 * A classe Produto representa um item disponível para venda em um sistema de
 * estoque ou comércio eletrônico. Cada produto possui um nome, quantidade
 * disponível em estoque e preço.
 */
public class Produto {
	
	private String nome;
	private int quantidade;
	private double preco;
	
	public Produto() {}
	
	public Produto(String nome, int quantidade, double preco) {		
		this.nome = nome;
		this.quantidade = quantidade;
		this.preco = preco;
	}

	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public int getQuantidade() {
		return quantidade;
	}
	
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	
	public double getPreco() {
		return preco;
	}
	
	public void setPreco(double preco) {
		this.preco = preco;
	}

	@Override
	public String toString() {
		return String.format(ConstantesControleEstoque.STR_PRODUTO, nome, quantidade, preco);
	}
	
}//class Produto
