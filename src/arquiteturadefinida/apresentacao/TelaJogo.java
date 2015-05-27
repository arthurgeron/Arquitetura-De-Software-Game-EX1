package arquiteturadefinida.apresentacao;

import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import arquiteturadefinida.logicajogo.Direcao;
import arquiteturadefinida.logicajogo.Elemento;
import arquiteturadefinida.logicajogo.Posicao;
import arquiteturadefinida.logicajogo.SaidaJogo;
import arquiteturadefinida.logicajogo.Tabuleiro;
import arquiteturadefinida.logicajogo.InteligenciaArtificial;

public class TelaJogo implements SaidaJogo {

	private Tabuleiro tabuleiro;
	private FabricaIcones fabricaIcones;
	private JFrame frame;
	private Timer iATimer;
	private ScheduledTasks iATasks;
	private List<InteligenciaArtificial> iAs = new ArrayList<InteligenciaArtificial>();

	public TelaJogo(Tabuleiro tabuleiro, FabricaIcones fabricaIcones) {
		this.tabuleiro = tabuleiro;
		this.fabricaIcones = fabricaIcones;
		
		for (int i = 0; i < tabuleiro.getNumeroLinhas(); i++) {
			for (int j = 0; j < tabuleiro.getNumeroColunas(); j++) {
				if(tabuleiro.elementoEm(new Posicao(i, j)).ehInimigo()) {
					iAs.add(new InteligenciaArtificial(tabuleiro,tabuleiro.elementoEm(new Posicao(i, j))));
				}
			}
		}
		
		frame = new JFrame();
		frame.setLayout(new GridLayout(tabuleiro.getNumeroLinhas(), tabuleiro.getNumeroColunas()));
		frame.addKeyListener(new TecladoListener());

		preencherTela();

		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		iATimer = new Timer();
		iATasks = null;
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
		iATasks.cancel();//Para a thread
		JOptionPane.showMessageDialog(frame, "Ganhou!", "Ganhou!", JOptionPane.INFORMATION_MESSAGE);
		System.exit(0);
	}

	@Override
	public void perderJogo() {
		iATasks.cancel();//Para a thread
		JOptionPane.showMessageDialog(frame, "Perdeu!", "Perdeu!", JOptionPane.ERROR_MESSAGE);
		System.exit(0);
	}
	
	public void moverInimigos() {
		for(InteligenciaArtificial IA : iAs) {
			IA.moverInimigo();
		}
	}
	
	private class TecladoListener implements KeyListener {

		@Override
		public void keyTyped(KeyEvent e) {
		}

		@Override
		public void keyPressed(KeyEvent e) {
			//moverInimigos();
			Direcao direcao = Direcao.comCodigo(e.getKeyCode());
			Elemento elemento;
			
			if (direcao != null) {
				switch(e.getKeyCode()) {
				case 68:
					if(tabuleiro.acharPosicaoDe(Elemento.PERSONAGEM2)!=null){
						elemento = Elemento.PERSONAGEM2;
					}
					else{
						return;
					}
					break;
				case 65:
					if(tabuleiro.acharPosicaoDe(Elemento.PERSONAGEM2)!=null){
						elemento = Elemento.PERSONAGEM2;
					}
					else{
						return;
					}
					break;
					
				case 83:
					if(tabuleiro.acharPosicaoDe(Elemento.PERSONAGEM2)!=null){
						elemento = Elemento.PERSONAGEM2;
					}
					else{
						return;
					}
					break;
					
				case 87:
					if(tabuleiro.acharPosicaoDe(Elemento.PERSONAGEM2)!=null){
						elemento = Elemento.PERSONAGEM2;
					}
					else{
						return;
					}
					break;
				
				default:
					if(tabuleiro.acharPosicaoDe(Elemento.PERSONAGEM)!=null){
						elemento = Elemento.PERSONAGEM;
					}
					else {
						return;
					}
					break;
				}
				tabuleiro.fazerMovimento(direcao, elemento);
			}
			if(iATasks==null) {
				iATasks = new ScheduledTasks();
				iATimer.schedule(iATasks, 0 , 150);
			}
			//iA.moverInimigo();
		}

		@Override
		public void keyReleased(KeyEvent e) {
			
		}

	}
	
	
	public class ScheduledTasks extends TimerTask {
		 
			// Add your task here
		public void run() {
			moverInimigos(); // Mover inimigos
		}
	}


}
