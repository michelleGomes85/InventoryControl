package mcg.lpv.funcionalidades;

public enum Estoques {
	
	ALIMENTIFICIO("Gênero Alimentício"),
	LIMPEZA("Limpeza"),
	HIGIENE("Higiene");
	
	private String nome;

	private Estoques(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	
	
	
}//enum Estoques
