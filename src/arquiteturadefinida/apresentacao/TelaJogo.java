package arquiteturadefinida.apresentacao;

import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import arquiteturadefinida.logicajogo.Direcao;
import arquiteturadefinida.logicajogo.Elemento;
import arquiteturadefinida.logicajogo.Posicao;
import arquiteturadefinida.logicajogo.SaidaJogo;
import arquiteturadefinida.logicajogo.Tabuleiro;

public class TelaJogo implements SaidaJogo {

	private Tabuleiro tabuleiro;
	private FabricaIcones fabricaIcones;
	private JFrame frame;
	InteligenciaArtificial iA;

	public TelaJogo(Tabuleiro tabuleiro, FabricaIcones fabricaIcones) {
		this.tabuleiro = tabuleiro;
		this.fabricaIcones = fabricaIcones;
		iA = new InteligenciaArtificial();
		
		frame = new JFrame();
		frame.setLayout(new GridLayout(tabuleiro.getNumeroLinhas(), tabuleiro.getNumeroColunas()));
		frame.addKeyListener(new TecladoListener());

		preencherTela();

		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		while(true) {
//		//	iA.acharCaminho(null);
//		}
	}

	private void preencherTela() {
		for (int i = 0; i < tabuleiro.getNumeroLinhas(); i++) {
			for (int j = 0; j < tabuleiro.getNumeroColunas(); j++) {
				frame.add(new JLabel(fabricaIcones.obterIcone(tabuleiro.elementoEm(new Posicao(i, j)))));
			}
		}
	}

	@Override
	public void iniciarJogo() {
		frame.setVisible(true);
	}

	@Override
	public void alterarElemento(Posicao posicao, Elemento elemento) {
		int indice = tabuleiro.getNumeroColunas() * posicao.getLinha() + posicao.getColuna();
		((JLabel) frame.getContentPane().getComponent(indice)).setIcon(fabricaIcones.obterIcone(elemento));
	}

	@Override
	public void passarDeFase() {
		JOptionPane.showMessageDialog(frame, "Ganhou!", "Ganhou!", JOptionPane.INFORMATION_MESSAGE);
		System.exit(0);
	}

	@Override
	public void perderJogo() {
		JOptionPane.showMessageDialog(frame, "Perdeu!", "Perdeu!", JOptionPane.ERROR_MESSAGE);
		System.exit(0);
	}

	private class TecladoListener implements KeyListener {

		@Override
		public void keyTyped(KeyEvent e) {
		}

		@Override
		public void keyPressed(KeyEvent e) {
			iA.moverInimigo();
			Direcao direcao = Direcao.comCodigo(e.getKeyCode());
			if (direcao != null) tabuleiro.fazerMovimento(direcao, Elemento.PERSONAGEM);
		}

		@Override
		public void keyReleased(KeyEvent e) {
			
		}

	}
	
	private class InteligenciaArtificial {
		
		Elemento inimigo;
		int altura, largura;
		int[] posicaoInimigo = new int[2];
		ArrayList<int[]> posicoesAndadasOuInvalidas = new ArrayList<int[]>();
		
		public InteligenciaArtificial() {
			altura = tabuleiro.getNumeroLinhas();
			largura = tabuleiro.getNumeroColunas();
			inimigo = Elemento.INIMIGO;
			posicaoInimigo[0] = tabuleiro.acharPosicaoDe(inimigo).getLinha();
			posicaoInimigo[1] = tabuleiro.acharPosicaoDe(inimigo).getColuna();

			for(int i = 0 ; i < altura ; i++) {
				for(int j = 0 ; j < largura ; j++) {
					if(tabuleiro.elementoEm(new Posicao(i,j)).getEhObstaculo()) {
						posicoesAndadasOuInvalidas.add(new int[]{i,j});
					}
				}
			}
		}
		public void moverInimigo() {
			if(tabuleiro.acharPosicaoDe(Elemento.INIMIGO)!= null) {
				Direcao direcao;
				direcao = acharCaminho(null, new Posicao(posicaoInimigo[0],posicaoInimigo[1]));
				if(direcao != null) {
					tabuleiro.fazerMovimento(direcao, inimigo);
					posicaoInimigo[0] = posicaoInimigo[0] + direcao.getIncrementoLinha();
					posicaoInimigo[1] = posicaoInimigo[1] + direcao.getIncrementoColuna();
				}
			}
		}
		private Direcao acharCaminho(Direcao direcaoInicial, Posicao posicao) {
			int diferencaDeLinhas, diferencaDeColunas;
			diferencaDeLinhas = tabuleiro.acharPosicaoDe(Elemento.PERSONAGEM).getLinha() - posicao.getLinha();
			diferencaDeColunas = tabuleiro.acharPosicaoDe(Elemento.PERSONAGEM).getColuna() - posicao.getColuna();
			try {
				if(tabuleiro.elementoEm(posicao.somar(Direcao.BAIXO)).equals(Elemento.PERSONAGEM)) {
					return direcaoInicial == null ? Direcao.BAIXO : direcaoInicial;
				}
				else if(tabuleiro.elementoEm(posicao.somar(Direcao.CIMA)).equals(Elemento.PERSONAGEM)) {
					return direcaoInicial == null ? Direcao.CIMA : direcaoInicial;
				}
				else if(tabuleiro.elementoEm(posicao.somar(Direcao.DIREITA)).equals(Elemento.PERSONAGEM)) {
					return direcaoInicial == null ? Direcao.DIREITA : direcaoInicial;
				}
				else if(tabuleiro.elementoEm(posicao.somar(Direcao.ESQUERDA)).equals(Elemento.PERSONAGEM)) {
					return direcaoInicial == null ? Direcao.ESQUERDA : direcaoInicial;
				}
				
				if(
					diferencaDeLinhas==0 
					&&
				    (!posicoesAndadasOuInvalidas.contains(new int[]{posicao.somar(Direcao.DIREITA).getLinha(),posicao.getColuna()}) && !tabuleiro.posicaoEhInvalida(posicao.somar(Direcao.DIREITA))) 
				    && diferencaDeColunas>0	
				    ) {
					
						return acharCaminho(direcaoInicial,posicao.somar(Direcao.DIREITA));
					}
				else if(
						diferencaDeLinhas==0 
						&&
					    (!posicoesAndadasOuInvalidas.contains(new int[]{posicao.somar(Direcao.ESQUERDA).getLinha(),posicao.getColuna()}) && !tabuleiro.posicaoEhInvalida(posicao.somar(Direcao.ESQUERDA))) 
					    && diferencaDeColunas<0	
					    ) {
						return acharCaminho(direcaoInicial,posicao.somar(Direcao.ESQUERDA));
					}
				
			
				if(
						diferencaDeColunas==0 
						&&
					    (!posicoesAndadasOuInvalidas.contains(new int[]{posicao.somar(Direcao.BAIXO).getLinha(),posicao.getColuna()}) && !tabuleiro.posicaoEhInvalida(posicao.somar(Direcao.BAIXO))) 
					    && diferencaDeLinhas>0	
					    ) {
						return acharCaminho(direcaoInicial,posicao.somar(Direcao.BAIXO));
					}
					else if (
							diferencaDeColunas==0 
							&&
						    (!posicoesAndadasOuInvalidas.contains(new int[]{posicao.somar(Direcao.CIMA).getLinha(),posicao.getColuna()}) && !tabuleiro.posicaoEhInvalida(posicao.somar(Direcao.CIMA))) 
						    && diferencaDeLinhas<0	
						    ) {
						return acharCaminho(direcaoInicial,posicao.somar(Direcao.CIMA));
					}
			
//			if(Math.abs(diferencaDeLinhas) > Math.abs(diferencaDeColunas)) {	
//				if(diferencaDeLinhas>0) {
//					if(!posicoesAndadasOuInvalidas.contains(new int[]{posicao.somar(Direcao.BAIXO).getLinha(),posicao.somar(Direcao.BAIXO).getColuna()}) && !tabuleiro.posicaoEhInvalida(posicao.somar(Direcao.BAIXO))) {
//						return acharCaminho(direcaoInicial, posicao.somar(Direcao.BAIXO));
//					
//
//				}
//				else {
//					if(!posicoesAndadasOuInvalidas.contains(new int[]{posicao.somar(Direcao.CIMA).getLinha(),posicao.getColuna()}) && !tabuleiro.posicaoEhInvalida(posicao.somar(Direcao.CIMA))) {
//						return acharCaminho(direcaoInicial, posicao.somar(Direcao.CIMA));
//						
//					} 
//				}
//			}
//			else {
//				if(diferencaDeColunas>0) {
//				//para esquerda
//					if(!posicoesAndadasOuInvalidas.contains(new int[]{posicao.somar(Direcao.ESQUERDA).getLinha() ,posicao.somar(Direcao.ESQUERDA).getColuna()}) && !tabuleiro.posicaoEhInvalida(posicao.somar(Direcao.ESQUERDA))) {
//						return acharCaminho(direcaoInicial, posicao.somar(Direcao.ESQUERDA));
//						
//					}
//				}
//				//para direita
//				else {
//					if(!posicoesAndadasOuInvalidas.contains(new int[]{posicao.somar(Direcao.DIREITA).getLinha(),posicao.somar(Direcao.DIREITA).getColuna()}) && !tabuleiro.posicaoEhInvalida(posicao.somar(Direcao.DIREITA))) {
//						return acharCaminho(direcaoInicial, posicao.somar(Direcao.DIREITA));
//						
//					}
//				}
//				//para cima
//			}	
				return null;
//			}
			}
			catch(Exception exception) {
				System.out.println(exception);
				return null;
			}
		}
	}

}
