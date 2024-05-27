package baseclasses;

import java.util.Optional;

public interface Stock {
	
	/**
	 * Acrescenta um produto no estoque.
	 * @param product - produto que será adiciona
	 */
	public abstract void add(Product product);
	
	/**
	 * Obtém um produto do estoque segundo o nome do produto
	 * @param name - nome do produto a ser consultado
	 * @return <Optional> objeto Produto
	 */
	public abstract Optional<Product> consult(String name);
	
	/**
	 * Fornece o número de produtos armazenados no estoque 
	 * @return int quantidade produtos no estoque
	 */
	public abstract int size();
	
	/**
	 * Obtém um relatório de todos os Produtos do estoque
	 * @return Produto[] vetor de produtos 
	 */
	public abstract Product[] report();
	
}//class Estoque
