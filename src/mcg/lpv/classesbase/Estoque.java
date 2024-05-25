package mcg.lpv.classesbase;

import java.util.Optional;

public interface Estoque {
	
	/**
	 * Acrescenta um produto no estoque.
	 * @param produto - produto que será adiciona
	 */
	public abstract void adicionar(Produto produto);
	
	/**
	 * Obtém um produto do estoque segundo o nome do produto
	 * @param nome
	 * @return <Optional> objeto Produto
	 */
	public abstract Optional<Produto> consultar(String nome);
	
	/**
	 * Fornece o número de produtos armazenados no estoque 
	 * @return int quantidade produtos no estoque
	 */
	public abstract int tamanho();
	
	/**
	 * Obtém um relatório de todos os Produtos do estoque
	 * @return Produto[] vetor de produtos 
	 */
	public abstract Produto[] relatorio();
	
}//class Estoque
