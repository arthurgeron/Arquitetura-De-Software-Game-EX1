package arquiteturadefinida.logicajogo;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Personagem implements Elemento {

	
	private final String caminhoImagem = "/personagem.png";
	private boolean obstaculo,inimigo,jogador;
	private Direcao direcao; 
	private TecladoListener listener;
	private Tabuleiro tabuleiro;
	public Personagem(Direcao direcao, Tabuleiro tabuleiro) {
		atribuirValoresPadroes();
		this.direcao = direcao;
		this.tabuleiro = tabuleiro;
	}
	
	public Personagem() {
		atribuirValoresPadroes();
	}
	
	private void atribuirValoresPadroes(){
		obstaculo = false;
		inimigo = false;
		jogador = true;
		listener = new TecladoListener();
	}
	
	@Override
	public void setTabuleiro(Tabuleiro tabuleiro){
		this.tabuleiro = tabuleiro;
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
	
	private Personagem getPersonagem(){
		return this;
	}

	@Override
	public boolean ehObstaculo() {
		// TODO Auto-generated method stub
		return obstaculo;
	}
	
	private Direcao getDirecao(){
		return direcao;
	}

	@Override
	public TecladoListener getListener(){
		return listener;
	}
	
	public class TecladoListener implements KeyListener {
		
		Personagem personagem;
		
		public TecladoListener(){
			this.personagem = getPersonagem();
		}
		
		
		
		@Override
		public void keyTyped(KeyEvent e) {
		}
		
		@Override
		public void keyPressed(KeyEvent e) {
	
			Direcao direcao;
			if(getDirecao().getClass().getName().contains("Direcao_TecladoDirecional")){
				direcao = Direcao_TecladoDirecional.comCodigo(e.getKeyCode());
				if(direcao!=null && tabuleiro.acharPosicaoDe(personagem)!=null){
					tabuleiro.fazerMovimentoPersonagem(direcao,personagem);
				}
			}
			else if(getDirecao().getClass().getName().contains("Direcao_TecladoWASD")){
				direcao = Direcao_TecladoWASD.comCodigo(e.getKeyCode());
				if(direcao!=null && tabuleiro.acharPosicaoDe(personagem)!=null){
					tabuleiro.fazerMovimentoPersonagem(direcao,personagem);
				}
			}

		}

		@Override
		public void keyReleased(KeyEvent e) {
			
		}

	}

	@Override
	public void associarTabuleiro(Tabuleiro tabuleiro) {
		// TODO Auto-generated method stub
		this.tabuleiro = tabuleiro;
	}

	@Override
	public void setDirecao(Direcao direcao) {
		// TODO Auto-generated method stub
		this.direcao = direcao;
		
	}




}
