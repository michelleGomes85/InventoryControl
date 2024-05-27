package baseclasses;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * A classe Warehouse (Almoxarifado) representa um tipo de estoque onde 
 * produtos podem ser armazenados e gerenciados. 
 * Implementa a interface Stock.
 */
public class Warehouse implements Stock {
	
	private List<Product> stock;
	private String typeStock;
	
	/**
	 * Construtor que inicializa a lista de produtos e define
	 * o nome do tipo de estoque.
	 * 
	 * @param typeStock O nome do tipo de estoque.
	 */
	public Warehouse(String typeStock) {
		stock = new ArrayList<>();
		
		this.typeStock = typeStock;
	}
	
	public String getTypeStock() {
		return typeStock;
	}

	public void setTypeStock(String typeStock) {
		this.typeStock = typeStock;
	}
	
	/**
	 * Adiciona um produto ao estoque.
	 * 
	 * @param product O produto a ser adicionado.
	 */
	@Override
	public void add(Product product) {
		stock.add(product);
	}
	
	/**
	 * Consulta um produto no estoque pelo nome.
	 * 
	 * @param name O nome do produto a ser consultado.
	 * @return Um objeto Optional contendo o produto, se encontrado; ou vazio caso
	 *         contrário.
	 */
	@Override
	public Optional<Product> consult(String name) {
		
		for (Product product : stock)
			if (name.equalsIgnoreCase(product.getName()))
				return Optional.of(product);
		
		return Optional.ofNullable(null);
	}
	
	/**
	 * Retorna o número de produtos armazenados no estoque.
	 * 
	 * @return A quantidade de produtos no estoque.
	 */
	@Override
	public int size() {
		return stock.size();
	}
	
	/**
	 * Retorna um relatório contendo todos os produtos do estoque.
	 * 
	 * @return Um array contendo os produtos do estoque.
	 */
	@Override
	public Product[] report() {
		return stock.toArray(new Product[0]);
	}
	
	/**
	 * Calcula a quantidade total de produtos no estoque.
	 * 
	 * @return A quantidade total de produtos no estoque.
	 */
	public int quantidadeProdutosEstoque() {
		
		Product[] products = report();
		int quantity = 0;
		
		for (int index = 0; index < products.length; index++)
			quantity += products[index].getAmount();
		
		return quantity;
	}
	
	/**
	 * Calcula o valor total do estoque.
	 * 
	 * @return O valor total do estoque.
	 */
	public double amount() {
		
		Product[] products = report();
		double amount = 0;
		
		for (int index = 0; index < products.length; index++)
			amount += products[index].getPrice() * products[index].getAmount(); 
		
		return amount;
	}
	
	/**
	 * Atualiza as informações de um produto no estoque.
	 * 
	 * @param current O produto antigo, antes das atualizações.
	 * @param updated O produto com as informações atualizadas.
	 */
	public void updateData(Product current, Product updated) {
		
		int position = stock.indexOf(current);
		
		if(position >= 0)
			stock.set(position, updated);
	}
}//class Warehouse