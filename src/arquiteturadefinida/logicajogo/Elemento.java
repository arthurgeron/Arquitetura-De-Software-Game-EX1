package arquiteturadefinida.logicajogo;

public enum Elemento {

	AGUA("/agua.png", true),
	MACA("/maca.png"),
	PERSONAGEM("/personagem.png"),
	INIMIGO("/inimigo.png"),
	GRAMA("/grama.png"),
	PORTAL("/passagem.png"),
	;

	private final String caminhoImagem;
	private boolean obstaculo;
	
	Elemento(String caminhoImagem) {
		this.caminhoImagem = caminhoImagem;
		obstaculo = false;
	}
	Elemento(String caminhoImagem, boolean obstaculo) {
		this.caminhoImagem = caminhoImagem;
		this.obstaculo = obstaculo;
	}

	public String getCaminhoImagem() {
		return caminhoImagem;
	}
	
	public boolean getEhObstaculo() {
		return obstaculo;
	}
	
	

}
