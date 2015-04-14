package apresentacao;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import logica.Elemento;
import logica.FabricaIcones;
import logica.Tabuleiro;

public class TelaJogo {
	private JFrame frame;
	private Tabuleiro tabuleiro;
	private FabricaIcones fabricaIcones;
	public TelaJogo() {
		frame = new JFrame();
		fabricaIcones = new FabricaIcones();
		
		int linhas = Integer.valueOf( JOptionPane.showInputDialog(frame,"Digite a quantidade de linhas do seu tabuleiro: "));
		int colunas = Integer.valueOf( JOptionPane.showInputDialog(frame,"Digite a quantidade de colunas do seu tabuleiro: "));
		
		Elemento[][] matrizElementos = new Elemento[linhas][colunas];
		
		String caminhoImagemAgua,caminhoImagemMaca,caminhoImagemPersonagem,caminhoImagemGrama,caminhoImagemPassagem;
		caminhoImagemAgua = JOptionPane.showInputDialog(frame,"Digite a o caminho para a imagem do objeto Água: ");
		caminhoImagemMaca = JOptionPane.showInputDialog(frame,"Digite a o caminho para a imagem do objeto Maça: ");
		caminhoImagemPersonagem = JOptionPane.showInputDialog(frame,"Digite a o caminho para a imagem do objeto Personagem: ");
		caminhoImagemGrama = JOptionPane.showInputDialog(frame,"Digite a o caminho para a imagem do objeto Grama: ");
		caminhoImagemPassagem = JOptionPane.showInputDialog(frame,"Digite a o caminho para a imagem do objeto Passagem: ");
		
		fabricaIcones.addCaminhoImagem(caminhoImagemAgua);
		fabricaIcones.addCaminhoImagem(caminhoImagemMaca);
		fabricaIcones.addCaminhoImagem(caminhoImagemGrama);
		fabricaIcones.addCaminhoImagem(caminhoImagemPersonagem);
		fabricaIcones.addCaminhoImagem(caminhoImagemPassagem);
		Elemento.AGUA______.setIcone( fabricaIcones.getIcone(caminhoImagemAgua));
		Elemento.MACA______.setIcone(fabricaIcones.getIcone(caminhoImagemMaca));
		Elemento.GRAMA_____.setIcone(fabricaIcones.getIcone(caminhoImagemGrama));
		Elemento.PERSONAGEM.setIcone(fabricaIcones.getIcone(caminhoImagemPersonagem));
		Elemento.PASSAGEM__.setIcone(fabricaIcones.getIcone(caminhoImagemPassagem));
		
		tabuleiro = new Tabuleiro(matrizElementos, frame);	
	}
	public TelaJogo(Elemento[][] disposicaoInicial){
		frame = new JFrame();
		fabricaIcones = new FabricaIcones();
		
		String caminhoImagemAgua,caminhoImagemMaca,caminhoImagemPersonagem,caminhoImagemGrama,caminhoImagemPassagem;
		caminhoImagemAgua = JOptionPane.showInputDialog(frame,"Digite a o caminho para a imagem do objeto Água: ");
		caminhoImagemMaca = JOptionPane.showInputDialog(frame,"Digite a o caminho para a imagem do objeto Maça: ");
		caminhoImagemPersonagem = JOptionPane.showInputDialog(frame,"Digite a o caminho para a imagem do objeto Personagem: ");
		caminhoImagemGrama = JOptionPane.showInputDialog(frame,"Digite a o caminho para a imagem do objeto Grama: ");
		caminhoImagemPassagem = JOptionPane.showInputDialog(frame,"Digite a o caminho para a imagem do objeto Passagem: ");
		
		fabricaIcones.addCaminhoImagem(caminhoImagemAgua);
		fabricaIcones.addCaminhoImagem(caminhoImagemMaca);
		fabricaIcones.addCaminhoImagem(caminhoImagemGrama);
		fabricaIcones.addCaminhoImagem(caminhoImagemPersonagem);
		fabricaIcones.addCaminhoImagem(caminhoImagemPassagem);
		Elemento.AGUA______.setIcone( fabricaIcones.getIcone(caminhoImagemAgua));
		Elemento.MACA______.setIcone(fabricaIcones.getIcone(caminhoImagemMaca));
		Elemento.GRAMA_____.setIcone(fabricaIcones.getIcone(caminhoImagemGrama));
		Elemento.PERSONAGEM.setIcone(fabricaIcones.getIcone(caminhoImagemPersonagem));
		Elemento.PASSAGEM__.setIcone(fabricaIcones.getIcone(caminhoImagemPassagem));
		
		tabuleiro = new Tabuleiro(disposicaoInicial, frame);
	}
	public TelaJogo(Elemento[][] disposicaoInicial, String caminhoImagemAgua ,String caminhoImagemMaca ,String caminhoImagemPersonagem ,String caminhoImagemGrama ,String caminhoImagemPassagem){
		frame = new JFrame();
		fabricaIcones = new FabricaIcones();
		
		fabricaIcones.addCaminhoImagem(caminhoImagemAgua);
		fabricaIcones.addCaminhoImagem(caminhoImagemMaca);
		fabricaIcones.addCaminhoImagem(caminhoImagemGrama);
		fabricaIcones.addCaminhoImagem(caminhoImagemPersonagem);
		fabricaIcones.addCaminhoImagem(caminhoImagemPassagem);
		Elemento.AGUA______.setIcone( fabricaIcones.getIcone(caminhoImagemAgua));
		Elemento.MACA______.setIcone(fabricaIcones.getIcone(caminhoImagemMaca));
		Elemento.GRAMA_____.setIcone(fabricaIcones.getIcone(caminhoImagemGrama));
		Elemento.PERSONAGEM.setIcone(fabricaIcones.getIcone(caminhoImagemPersonagem));
		Elemento.PASSAGEM__.setIcone(fabricaIcones.getIcone(caminhoImagemPassagem));
		
		tabuleiro = new Tabuleiro(disposicaoInicial, frame);
	}

}
