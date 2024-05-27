package util;

public enum Stocks {
	
	FOOD("Gênero Alimentício"),
	CLEANING("Limpeza"),
	HYGIENE("Higiene");
	
	private String title;

	private Stocks(String title) {
		this.title = title;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public static String[] getString() {
		
		String[] stocksStr = new String[Stocks.values().length];
		int index = 0;
		
		for (Stocks stock : Stocks.values())
			stocksStr[index++] = stock.getTitle();
		
		return stocksStr;
	}
}//enum Estoques
