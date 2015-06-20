package arquiteturadefinida.logicajogo;

import arquiteturadefinida.logicajogo.Personagem.TecladoListener;

public interface Elemento {
	
	String caminhoImagem = null;
	boolean obstaculo = false,inimigo = false ,jogador = false;
	
	public boolean ehInimigo() ;
	
	public boolean ehJogador() ;
	

	public String getCaminhoImagem() ;
	
	public boolean ehObstaculo();
	
	public TecladoListener getListener();
	
	public void associarTabuleiro(Tabuleiro tabuleiro);

	public void setDirecao(Direcao direcao);
	
	public void setTabuleiro(Tabuleiro tabuleiro);
	
}
