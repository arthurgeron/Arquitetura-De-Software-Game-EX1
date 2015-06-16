package arquiteturadefinida.logicajogo;

public enum Elemento {

	AGUA("/agua.png", true, false, false),
	MACA("/maca.png", true, false, false),
	PERSONAGEM("/personagem.png",false,false,true),
	PERSONAGEM2("/personagem.png",false,false,true),
	INIMIGO("/inimigo.png",true,true,false),
	INIMIGO2("/inimigo.png",true,true,false),
	INIMIGO3("/inimigo.png",true,true,false),
	INIMIGO4("/inimigo.png",true,true,false),
	GRAMA("/grama.png"),
	PORTAL("/passagem.png", true, false, false)
	;

	private final String caminhoImagem;
	private boolean obstaculo,inimigo,jogador;
	
	Elemento(String caminhoImagem) {
		this.caminhoImagem = caminhoImagem;
		obstaculo = false;
		inimigo = false;
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
	
	public boolean ehObstaculo() {
		return obstaculo;
	}
	
	

}
