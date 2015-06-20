package arquiteturadefinida.logicajogo;

import arquiteturadefinida.logicajogo.Personagem.TecladoListener;

public class Inimigo implements Elemento {
	
	private final String caminhoImagem = "/inimigo.png";
	private boolean obstaculo,inimigo,jogador;
	
	public Inimigo() {
		// TODO Auto-generated constructor stub
		obstaculo = true;
		inimigo = true;
		jogador = false;
	}

	@Override
	public boolean ehInimigo() {
		// TODO Auto-generated method stub
		return inimigo;
	}

	@Override
	public boolean ehJogador() {
		// TODO Auto-generated method stub
		return jogador;
	}

	@Override
	public String getCaminhoImagem() {
		// TODO Auto-generated method stub
		return caminhoImagem;
	}

	@Override
	public boolean ehObstaculo() {
		// TODO Auto-generated method stub
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

}
