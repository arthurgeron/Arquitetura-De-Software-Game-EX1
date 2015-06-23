package arquiteturadefinida.logicajogo;

import arquiteturadefinida.logicajogo.Personagem.TecladoListener;

public enum ElementoEstatico implements Elemento{

	AGUA("/agua.png", true),
	MACA("/maca.png", true),
	GRAMA("/grama.png"),
	PORTAL("/passagem.png", true)
	;

	private final String caminhoImagem;
	private boolean obstaculo,inimigo,jogador;
	
	ElementoEstatico(String caminhoImagem) {
		this.caminhoImagem = caminhoImagem;
		obstaculo = false;
		inimigo = false;
		jogador = false;
	}
	
	ElementoEstatico(String caminhoImagem, boolean obstaculo) {
		this.caminhoImagem = caminhoImagem;
		this.obstaculo = obstaculo;
		jogador = false;
		inimigo = false;
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

	@Override
	public TecladoListener getListener() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void associarTabuleiro(Tabuleiro tabuleiro) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setDirecao(Direcao direcao) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setTabuleiro(Tabuleiro tabuleiro) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Direcao getDirecao() {
		return null;
		// TODO Auto-generated method stub
		
	}
	
	

}
