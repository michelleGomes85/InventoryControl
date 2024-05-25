package mcg.lpv.classesbase.controle;

import mcg.lpv.classesbase.Almoxarifado;
import mcg.lpv.funcionalidades.Estoques;

/**
 * A classe ControleEstoque é responsável por inicializar e controlar os
 * estoques do sistema.
 */
public class ControleEstoque {
	
	/**
	 * Construtor padrão da classe ControleEstoque.
	 * Inicializa os estoques do sistema.
	 */
	public ControleEstoque() {
		
		Almoxarifado[] estoques = {	
				new Almoxarifado(Estoques.ALIMENTIFICIO.getNome()), 
				new Almoxarifado(Estoques.LIMPEZA.getNome()), 
				new Almoxarifado(Estoques.HIGIENE.getNome())
		};
	}
	
	/**
	 * Método principal que inicia o controle de estoque.
	 * 
	 * @param args Os argumentos passados pela linha de comando (não utilizados
	 *             neste caso).
	 */
	public static void main(String[] args) {
		new ControleEstoque();
	}
	
}//class ControleEstoque