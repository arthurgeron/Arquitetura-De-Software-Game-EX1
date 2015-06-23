package arquiteturadefinida.logicajogo;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;


public class Tabuleiro {

	private Elemento[][] matriz;
	private SaidaJogo saida;
	private Posicao posicaoDoPortalOculto;
	private ScheduledTasks iATasks;
	private Timer iATimer;
	private boolean jogadorAtingiuPortal = false, jogadorAindaNaoSeMoveu = true;
	private List<InteligenciaArtificial> iAs = new ArrayList<InteligenciaArtificial>();
	public Tabuleiro(Elemento[][] matriz) {
		int contagemDePersonagens = 0;
		this.matriz = matriz;
		iATimer = new Timer();

		for (int i = 0; i < getNumeroLinhas(); i++) {
 			for (int j = 0; j < getNumeroColunas(); j++) {
				if(elementoEm(new Posicao(i, j)) instanceof Personagem) {
					
					if(elementoEm(new Posicao(i, j)).getDirecao()==null){
						if(contagemDePersonagens==0){
							(elementoEm(new Posicao(i, j))).setDirecao(Direcao_TecladoDirecional.DIRECIONAL);
							contagemDePersonagens++;
						}
						else if (contagemDePersonagens==1){
							(elementoEm(new Posicao(i, j))).setDirecao(Direcao_TecladoWASD.WASD);
							contagemDePersonagens++;
						}
					}
					(elementoEm(new Posicao(i, j))).setTabuleiro(this);
				}
				else if(elementoEm(new Posicao(i, j)) instanceof Inimigo){
					iAs.add(new InteligenciaArtificial(this,elementoEm(new Posicao(i, j))));
				}
			}
		}
		
		
	}

	public void setSaida(SaidaJogo saida) {
		this.saida = saida;
	}

	public void iniciarJogo() {
		ocultarPortal();
		saida.iniciarJogo();
	}

	public int getNumeroLinhas() {
		return matriz.length;
	}

	public int getNumeroColunas() {
		return matriz[0].length;
	}
	
	private void acordarInimigos(){
		iATasks = new ScheduledTasks();
		iATimer.schedule(iATasks, 0 , 150);
	}

	public Elemento elementoEm(Posicao posicao) {
			return matriz[posicao.getLinha()][posicao.getColuna()] ;
		

	}
	
	public void AdicionarKeyListenersAoFrame(JFrame frame){
		for(int i = 0 ; i < matriz.length ; i++) {
			for(int j = 0 ; j< matriz[0].length ; j++){
				if(elementoEm(new Posicao(i,j)).ehJogador()){
					frame.addKeyListener(elementoEm(new Posicao(i,j)).getListener());
				}
			}
		}
	}
	
	public void fazerMovimentoInimigo(Direcao d, Elemento inimigo) {
		
		if(!(inimigo instanceof Inimigo)) {
			return;
		}
		
		Posicao posicaoAntiga = acharPosicaoDe(inimigo);
		Posicao posicaoNova;
		
		posicaoNova = posicaoAntiga.somar(d);
		
		if (posicaoEhInvalida(posicaoNova)) return;
		
		Elemento elementoAtingido = elementoEm(posicaoNova);
		
		if(!(elementoAtingido.ehObstaculo())) {
			alterarElemento(posicaoAntiga, ElementoEstatico.GRAMA);
			if(elementoAtingido instanceof Personagem) {
				jogadorMorreu();
			}
			alterarElemento(posicaoNova, inimigo);
			
		}

	}
	
	public void fazerMovimentoPersonagem(Direcao d, Elemento personagem) {

		Posicao posicaoAntiga = acharPosicaoDe(personagem);
		Posicao posicaoNova;
		
		if(jogadorAindaNaoSeMoveu) {
			acordarInimigos();
			jogadorAindaNaoSeMoveu = false;
		}
		
		posicaoNova = posicaoAntiga.somar(d);
		
		if (posicaoEhInvalida(posicaoNova)) return;
		
		Elemento elementoAtingido = elementoEm(posicaoNova);
		
		alterarElemento(posicaoAntiga, ElementoEstatico.GRAMA);
		
		if(elementoAtingido.equals(ElementoEstatico.AGUA) || elementoAtingido.ehInimigo() ) {
			jogadorMorreu();
		}
		else if(elementoAtingido.equals(ElementoEstatico.PORTAL)) {
			jogadorAtingiuPortal = true;
			if(quantidadeDeJogadoresRestantes() == 0){
				saida.passarDeFase();
			}
		}
		else{
			alterarElemento(posicaoNova, personagem);
			if (quantidadeMacasRestantes() == 0) {
				reexibirPortal();
			}
		}
	

	}
	
	private void jogadorMorreu(){
		if(quantidadeDeJogadoresRestantes() == 0)
		{
			iATasks.cancel();//Para a thread
			if(jogadorAtingiuPortal) {
				saida.passarDeFase();
			}
			else {
				iATasks.cancel();
				saida.perderJogo();
			}
		}
	}
	
	private void moverInimigos() {
		for(InteligenciaArtificial IA : iAs) {
			IA.moverInimigo();
		}
	}
	
	private void ocultarPortal() {
		posicaoDoPortalOculto = acharPosicaoDe(ElementoEstatico.PORTAL);
		alterarElemento(posicaoDoPortalOculto, ElementoEstatico.GRAMA);
	}

	private void reexibirPortal() {
		alterarElemento(posicaoDoPortalOculto, ElementoEstatico.PORTAL);
	}

	private void alterarElemento(Posicao posicao, Elemento e) {
		matriz[posicao.getLinha()][posicao.getColuna()] = e;
		saida.alterarElemento(posicao, e);
	}

	private int quantidadeMacasRestantes() {
		int ret = 0;

		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz[i].length; j++) {
				if (matriz[i][j] == ElementoEstatico.MACA) ++ret;
			}
		}

		return ret;
	}
	
	private int quantidadeDeJogadoresRestantes() {
		int ret = 0;

		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz[i].length; j++) {
				if (matriz[i][j].ehJogador()) ++ret;
			}
		}

		return ret;
	}

	public Posicao acharPosicaoDe(Elemento elemento) {
		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz[i].length; j++) {
				if (matriz[i][j] == elemento) {
					return new Posicao(i, j);
				}
			}
		}

		return null;
	}

	public boolean posicaoEhInvalida(Posicao p) {
		return p.getLinha() < 0 || p.getLinha() >= getNumeroLinhas()
				|| p.getColuna() < 0 || p.getColuna() >= getNumeroColunas();
	}
	
	public class ScheduledTasks extends TimerTask {
		 
			// Add your task here
		public void run() {
			moverInimigos(); // Mover inimigos
		}
	}

}
