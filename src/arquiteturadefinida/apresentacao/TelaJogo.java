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
				direcao = acharCaminho(new Posicao(posicaoInimigo[0],posicaoInimigo[1]));
				if(direcao != null) {
					tabuleiro.fazerMovimento(direcao, inimigo);
					posicaoInimigo[0] = posicaoInimigo[0] + direcao.getIncrementoLinha();
					posicaoInimigo[1] = posicaoInimigo[1] + direcao.getIncrementoColuna();
				}
			}
		}
		private Direcao acharCaminho(Posicao posicao) {
			int diferencaDeLinhas, diferencaDeColunas;
			diferencaDeLinhas = tabuleiro.acharPosicaoDe(Elemento.PERSONAGEM).getLinha() - posicao.getLinha();
			diferencaDeColunas = tabuleiro.acharPosicaoDe(Elemento.PERSONAGEM).getColuna() - posicao.getColuna();
			try {

				//para baixo
			if(Math.abs(diferencaDeLinhas) > Math.abs(diferencaDeColunas)) {	
				if(diferencaDeLinhas>0) {
					if(!posicoesAndadasOuInvalidas.contains(new int[]{posicao.getLinha() + 1,posicao.getColuna()}) && !tabuleiro.posicaoEhInvalida(new Posicao(posicao.getLinha() + 1, posicao.getColuna()))) {
						if(tabuleiro.elementoEm(new Posicao(posicao.getLinha() + 1,posicao.getColuna())).equals(Elemento.PERSONAGEM)) {
							return Direcao.BAIXO;
						}
						else { 
							return acharCaminho(new Posicao(posicao.getLinha() + 1, posicao.getColuna()));
						}

					}
				}
				else {
					if(!posicoesAndadasOuInvalidas.contains(new int[]{posicao.getLinha() - 1,posicao.getColuna()}) && !tabuleiro.posicaoEhInvalida(new Posicao(posicao.getLinha() - 1, posicao.getColuna()))) {
						if(tabuleiro.elementoEm(new Posicao(posicao.getLinha() - 1,posicao.getColuna())).equals(Elemento.PERSONAGEM)) {
							return Direcao.CIMA;
						}
						else { 
							return acharCaminho(new Posicao(posicao.getLinha() - 1, posicao.getColuna()));
						}
					} 
				}
			}
			else {
				if(diferencaDeColunas>0) {
				//para esquerda
					if(!posicoesAndadasOuInvalidas.contains(new int[]{posicao.getLinha() ,posicao.getColuna() -1}) && !tabuleiro.posicaoEhInvalida(new Posicao(posicao.getLinha(), posicao.getColuna() -1))) {
						if(tabuleiro.elementoEm(new Posicao(posicao.getLinha() ,posicao.getColuna() -1)).equals(Elemento.PERSONAGEM)) {
							return Direcao.ESQUERDA;
						}
						else { 
							return acharCaminho(new Posicao(posicao.getLinha(), posicao.getColuna() - 1));
						}
					}
				}
				//para direita
				else {
					if(!posicoesAndadasOuInvalidas.contains(new int[]{posicao.getLinha(),posicao.getColuna() +1}) && !tabuleiro.posicaoEhInvalida(new Posicao(posicao.getLinha(), posicao.getColuna() +1))) {
						if(tabuleiro.elementoEm(new Posicao(posicao.getLinha(),posicao.getColuna() +1)).equals(Elemento.PERSONAGEM)) {
							return Direcao.DIREITA;
						}
						else { 
							return acharCaminho(new Posicao(posicao.getLinha() , posicao.getColuna() + 1));
						}
					}
				}
				//para cima
			}	
				return null;
			}
			catch(Exception exception) {
				System.out.println(exception);
				return null;
			}
		}
	}

}
