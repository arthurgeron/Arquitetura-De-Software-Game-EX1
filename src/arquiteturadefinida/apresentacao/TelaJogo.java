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

	public TelaJogo(Tabuleiro tabuleiro, FabricaIcones fabricaIcones) {
		this.tabuleiro = tabuleiro;
		this.fabricaIcones = fabricaIcones;
		
		
		
		frame = new JFrame();
		frame.setLayout(new GridLayout(tabuleiro.getNumeroLinhas(), tabuleiro.getNumeroColunas()));
		tabuleiro.AdicionarKeyListenersAoFrame(frame);
		//frame.addKeyListener(new TecladoListener());

		preencherTela();

		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
	
	
	
	
}
