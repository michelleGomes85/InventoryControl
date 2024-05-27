package util;

import java.awt.event.KeyEvent;

/**
 * Enumeração para representar as opções de menu.
 */
public enum MenuOption {
	
    REGISTER("Cadastrar", KeyEvent.VK_C),
    SEARCH("Pesquisar", KeyEvent.VK_P),
    CLOSE("Sair", KeyEvent.VK_S),
    QUANTITY_OF_PRODUCTS("Quantidade Produtos", KeyEvent.VK_Q),
    AMOUNT("Valor Total", KeyEvent.VK_T),
    REPORT("Relatório", KeyEvent.VK_R);

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
