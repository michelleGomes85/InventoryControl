package util;

import java.awt.event.KeyEvent;

public enum Stocks {
	
	FOOD("Alimentício", KeyEvent.VK_A),
	CLEANING("Limpeza", KeyEvent.VK_L),
	HYGIENE("Higiene", KeyEvent.VK_H);
	
	private String title;
	private int keyEvent;

	private Stocks(String title, int keyEvent) {
		this.title = title;
		this.keyEvent = keyEvent;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public int getKeyEvent() {
		return keyEvent;
	}

	public void setKeyEvent(int keyEvent) {
		this.keyEvent = keyEvent;
	}

	public static String[] getString() {
		
		String[] stocksStr = new String[Stocks.values().length];
		int index = 0;
		
		for (Stocks stock : Stocks.values())
			stocksStr[index++] = stock.getTitle();
		
		return stocksStr;
	}
}//enum Estoques
