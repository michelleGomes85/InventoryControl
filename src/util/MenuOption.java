package util;

import java.awt.event.KeyEvent;

/**
 * Enumeração para representar as opções de menu.
 */
public enum MenuOption {
	
    CADASTRAR("Cadastrar", KeyEvent.VK_C),
    PESQUISAR("Pesquisar", KeyEvent.VK_P),
    SAIR("Sair", KeyEvent.VK_S),
    QUANTIDADE_PRODUTOS("Quantidade Produtos", KeyEvent.VK_Q),
    VALOR_TOTAL("Valor Total", KeyEvent.VK_T),
    RELATORIO("Relatório", KeyEvent.VK_R);

    private final String title;
    private final int mnemonic;

    MenuOption(String title, int mnemonic) {
        this.title = title;
        this.mnemonic = mnemonic;
    }

    public String getTitle() {
        return title;
    }

    public int getMnemonic() {
        return mnemonic;
    }
}//enum MenuOption
