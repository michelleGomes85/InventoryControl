package util;

public interface ConstantesControleEstoque {
	
	String STR_PRODUTO = "Nome = %s\nQuantidade = %d\nPreço = R$ %1.2f";
	String TITULO_PROGRAMA = "Controle de Estoque";
	
	String URL_IMG = "/util/img/estoqueFundo.png";
	
	int[] TAMANHO_IGMENU = {492, 361},
		  TAMANHO_IGCADASTRO = {378, 205},
		  TAMANHO_IGPESQUISA = {307, 129},
		  TAMANHO_IGCONSULTA = {353, 220},
		  TAMANHO_IGESCOLHAESTOQUE = {321, 117},
		  TAMANHO_IGRELATORIO = {450, 298},
		  TAMANHO_IGPESQUISADIRETORIO = {860, 423};
	
	String MSG_ERROR_NOT_REGISTERED_PRODUCTS = "Ainda não foi cadastrado produtos"; 
	
	
}//interface ConstantesControleEstoque
