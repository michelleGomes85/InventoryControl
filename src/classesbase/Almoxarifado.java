package mcg.lpv.classesbase;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * A classe Almoxarifado representa um tipo de estoque onde 
 * produtos podem ser armazenados e gerenciados. 
 * Implementa a interface Estoque.
 */
public class Almoxarifado implements Estoque {
	
	private List<Produto> estoque;
	private String nomeTipoEstoque;
	
	/**
	 * Construtor da classe Almoxarifado que inicializa a lista de produtos e define
	 * o nome do tipo de estoque.
	 * 
	 * @param nomeTipoEstoque O nome do tipo de estoque.
	 */
	public Almoxarifado(String nomeTipoEstoque) {
		estoque = new ArrayList<>();
		
		this.nomeTipoEstoque = nomeTipoEstoque;
	}
	
	public String getNomeTipoEstoque() {
		return nomeTipoEstoque;
	}

	public void setNomeTipoEstoque(String nomeTipoEstoque) {
		this.nomeTipoEstoque = nomeTipoEstoque;
	}
	
	/**
	 * Adiciona um produto ao estoque.
	 * 
	 * @param produto O produto a ser adicionado.
	 */
	@Override
	public void adicionar(Produto produto) {
		estoque.add(produto);
	}
	
	/**
	 * Consulta um produto no estoque pelo nome.
	 * 
	 * @param nome O nome do produto a ser consultado.
	 * @return Um objeto Optional contendo o produto, se encontrado; ou vazio caso
	 *         contrário.
	 */
	@Override
	public Optional<Produto> consultar(String nome) {
		
		for (Produto produto : estoque)
			if (nome.equalsIgnoreCase(produto.getNome()))
				return Optional.of(produto);
		
		return Optional.ofNullable(null);
	}
	
	/**
	 * Retorna o número de produtos armazenados no estoque.
	 * 
	 * @return A quantidade de produtos no estoque.
	 */
	@Override
	public int tamanho() {
		return estoque.size();
	}
	
	/**
	 * Retorna um relatório contendo todos os produtos do estoque.
	 * 
	 * @return Um array contendo os produtos do estoque.
	 */
	@Override
	public Produto[] relatorio() {
		return estoque.toArray(new Produto[0]);
	}
	
	/**
	 * Calcula a quantidade total de produtos no estoque.
	 * 
	 * @return A quantidade total de produtos no estoque.
	 */
	public int quantidadeProdutosEstoque() {
		
		Produto[] produtos = relatorio();
		int quantidadeProdutos = 0;
		
		for (int indice = 0; indice < produtos.length; indice++)
			quantidadeProdutos += produtos[indice].getQuantidade();
		
		return quantidadeProdutos;
	}
	
	/**
	 * Calcula o valor total do estoque.
	 * 
	 * @return O valor total do estoque.
	 */
	public double valorTotalEstoque() {
		
		Produto[] produtos = relatorio();
		double valorTotalEstoque = 0;
		
		for (int indice = 0; indice < produtos.length; indice++)
			valorTotalEstoque += produtos[indice].getPreco() * produtos[indice].getQuantidade(); 
		
		return valorTotalEstoque;
	}
	
	/**
	 * Atualiza as informações de um produto no estoque.
	 * 
	 * @param produtoAtual O produto antigo, antes das atualizações.
	 * @param produtoAtualizado O produto com as informações atualizadas.
	 */
	public void atualizarDados(Produto produtoAtual, Produto produtoAtualizado) {
		
		int posicao = estoque.indexOf(produtoAtual);
		
		if(posicao >= 0)
			estoque.set(posicao, produtoAtualizado);
	}
}//class Almoxarifado