package baseclasses.control;

import baseclasses.Warehouse;
import baseclasses.control.gui.IgStockControl;
import util.Stocks;

/**
 * A classe StockControl é responsável por inicializar e controlar os
 * estoques do sistema.
 */
public class StockControl {
	
	/**
	 * Construtor padrão da classe ControleEstoque.
	 * Inicializa os estoques do sistema.
	 */
	public StockControl() {
		
		Warehouse[] stocks = {	
				new Warehouse(Stocks.FOOD),
				new Warehouse(Stocks.CLEANING),
				new Warehouse(Stocks.HYGIENE)
		};
		
		new IgStockControl(stocks);
	}
	
	/**
	 * Método principal que inicia o controle de estoque.
	 * 
	 * @param args Os argumentos passados pela linha de comando (não utilizados
	 *             neste caso).
	 */
	public static void main(String[] args) {
		new StockControl();
	}
	
}//class ControleEstoque