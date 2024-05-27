package util;

import java.awt.event.KeyEvent;

public enum LabelRegister {	
	
	STOCKS("Estoques", KeyEvent.VK_E),
	NAME("Nome", KeyEvent.VK_N),
	AMOUNT("Quantidade", KeyEvent.VK_Q),
	PRICE("Preço", KeyEvent.VK_P);
	
	private final String title;
    private final int mnemonic;
    
    private LabelRegister(String title, int mnemonic) {
		this.title = title;
		this.mnemonic = mnemonic;
	}
    
	public String getTitle() {
		return title;
	}
	
	public int getMnemonic() {
		return mnemonic;
	}
    
}//enum LabelCadastro
