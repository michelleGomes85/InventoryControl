package util;

public interface Constants {
	
	String STR_PRODUCT = "Nome = %s\nQuantidade = %d\nPreço = R$ %1.2f";
	
	String PROGRAM_TITLE = "Controle de Estoque";
	String REGISTRATION_TITLE = PROGRAM_TITLE + ": Cadastro";
	String SEARCH_TITLE = PROGRAM_TITLE + ": Pesquisa";
	String QUERY_TITLE = PROGRAM_TITLE + "Consulta";
	
	String QUANTITY_PRODUCTS_STOCK = "Quantidade Produtos Estoque";
	String TOTAL_STOCK_VALUE = "Valor total Produtos Estoque";
	
	String FORMAT_QUANTITY_PRODUCTS = " = %d produtos";
	String FORMAT_STOCK_VALUE = " = R$ %.2f";
	
	String URL_IMG = "/util/img/background.png";
	
	String EMPTY = "";
	String NEW_LINE = "\n";
	String TWO_DOTS = ":";
	String EQUAL = "=";
	
	int[] SIZE_IGMENU = {492, 361},
		  SIZE_IGREGISTER = {378, 205},
		  SIZE_IGSEARCH = {307, 129},
		  SIZE_IGQUERY = {353, 220},
		  SIZE_IGREPORT = {450, 298};
	
	String CANCEL_BUTTON = "Cancelar",
		   CANCEL_BUTTON_TIP = "Cancelar Operação",
		   RECORD_BUTTON = "Gravar",
		   RECORD_BUTTON_TIP = "Gravar produto",
		   SEARCH_BUTTON = "Pesquisar",
		   SEARCH_BUTTON_TIP = "Pesquisar Produto",
		   CLOSE_BUTTON = "Fechar",
		   CLOSE_BUTTON_TIP = "Fechar janela";
	
	String PRODUCT_LABEL = "Produto",
		   PRODUCT_LABEL_TIP = "Nome do Produto",
		   STOCK_LABEL = "Estoque";
	
	String MSG_REGISTERED_PRODUCT = "Produto Cadastrado";
	String MSG_UPDATE_PRODUCT = "Este produto já existe deseja atualiza-lo?";
	
	
	String MSG_ERROR_NOT_REGISTERED_PRODUCTS = "Ainda não foi cadastrado produtos"; 
	String MSG_ERROR_EMPTY_FIELDS = "Há algum campo sem responder";
	String MSG_ERROR_INVALID_VALUE = "Valor Inválido";
	String MSG_ERROR_PRODUCT_NOT_FOUND = "Produto não encontrado";
	
}//interface ConstantesControleEstoque
