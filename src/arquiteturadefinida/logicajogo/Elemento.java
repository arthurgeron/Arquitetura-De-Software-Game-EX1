package arquiteturadefinida.logicajogo;

public enum Elemento {

	AGUA("/agua.png", true),
	MACA("/maca.png", true),
	PERSONAGEM("/personagem.png",false,false,true),
	PERSONAGEM2("/personagem.png",false,false,true),
	INIMIGO("/inimigo.png",true,true),
	INIMIGO2("/inimigo.png",true,true),
	INIMIGO3("/inimigo.png",true,true),
	INIMIGO4("/inimigo.png",true,true),
	GRAMA("/grama.png"),
	PORTAL("/passagem.png", true)
	;

	private final String caminhoImagem;
	private boolean obstaculo,inimigo,jogador;
	
	Elemento(String caminhoImagem) {
		this.caminhoImagem = caminhoImagem;
		obstaculo = false;
		inimigo = false;
		jogador = false;
	}
	Elemento(String caminhoImagem, boolean obstaculo) {
		this.caminhoImagem = caminhoImagem;
		this.obstaculo = obstaculo;
		jogador = false;
	}
	Elemento(String caminhoImagem, boolean obstaculo, boolean inimigo) {
		this.caminhoImagem = caminhoImagem;
		this.inimigo = inimigo;
		this.obstaculo = obstaculo;
		jogador = false;
	}
	
	Elemento(String caminhoImagem, boolean obstaculo, boolean inimigo, boolean jogador) {
		this.caminhoImagem = caminhoImagem;
		this.inimigo = inimigo;
		this.obstaculo = obstaculo;
		this.jogador = jogador;
	}
	
	public boolean ehInimigo() {
		return inimigo;
	}
	
	public boolean ehJogador() {
		return jogador;
	}

	public String getCaminhoImagem() {
		return caminhoImagem;
	}
	
	public boolean getEhObstaculo() {
		return obstaculo;
	}
	
	

}
