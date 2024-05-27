package util;

public interface Constants {
	
	String STR_PRODUCT = "Nome = %s\nQuantidade = %d\nPreço = R$ %1.2f";
	
	String PROGRAM_TITLE = "Controle de Estoque";
	String REGISTRATION_TITLE = PROGRAM_TITLE + ": Cadastro";
	
	String URL_IMG = "/util/img/background.png";
	
	String EMPTY = "";
	
	int[] SIZE_IGMENU = {492, 361},
		  SIZE_IGREGISTER = {378, 205},
		  SIZE_IGSEARCH = {307, 129},
		  SIZE_IGQUERY = {353, 220},
		  SIZE_IGREPORT = {450, 298};
	
	String CANCEL_BUTTON = "Cancelar",
		   CANCEL_BUTTON_TIP = "Cancelar Operação",
		   RECORD_BUTTON = "Gravar",
		   RECORD_BUTTON_TIP = "Gravar produto";
	
	String MSG_REGISTERED_PRODUCT = "Produto Cadastrado";
	String MSG_UPDATE_PRODUCT = "Este produto já existe deseja atualiza-lo?";
	
	String MSG_ERROR_NOT_REGISTERED_PRODUCTS = "Ainda não foi cadastrado produtos"; 
	String MSG_ERROR_EMPTY_FIELDS = "Há algum campo sem responder";
	String MSG_ERROR_INVALID_VALUE = "Valor Inválido";
	
}//interface ConstantesControleEstoque
