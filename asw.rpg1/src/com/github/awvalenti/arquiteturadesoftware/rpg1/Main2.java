// Leia o código abaixo e responda às seguintes questões:
//
//
// 1) Do ponto de vista do usuário, que problemas existem no jogo?
//
//
// 2) Do ponto de vista do programador, que problemas existem no jogo?
//
//
// 3) Diga em que linhas do código está localizada a lógica que cuida de:
// 	a) Carregar as imagens usadas no jogos
// 	b) Definir a quantidade de linhas e colunas do tabuleiro
// 	c) Manipular o objeto JFrame, que representa a janela do jogo
// 	d) Verificar se o jogo já acabou
//
//
// 4) Deseja-se fazer algumas mudanças no jogo. Para fazê-las, é necessário alterar
//    o código de determinadas maneiras. Explique, com palavras, que alterações no código
//    são necessárias para cada mudança desejada.
// 	a) Adicionar um novo elemento: moeda
// 	b) Avançar duas casas por vez quando segurar a tecla SHIFT
// 	c) Controlar o personagem com o mouse, clicando nas casas adjacentes
// 	d) Criar novas fases, com mais maçãs
// 	e) Criar novas fases, com tamanho maior do que 10x15
//
package com.github.awvalenti.arquiteturadesoftware.rpg1;

import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

//import com.github.awvalenti.arquiteturadesoftware.rpg1.versao1.procedimental.Main;

public class Main2 {

	int coletados;
	static ArrayList<Entidade> entidades = new ArrayList<Entidade>();
	
	
	//Declara o tabuleiro com a escolha do tamanho	
	
	
	public static void main(String[] args) throws IOException {
		Tabuleiro tabuleiro = new Tabuleiro(Integer.valueOf(JOptionPane.showInputDialog("Digite a largura do tabuleiro:")),Integer.valueOf(JOptionPane.showInputDialog("Digite a altura do tabuleiro: ")),entidades);
		final int numeroDeMacas;
		int numeroDeMacasTemp = 0;
		
		entidades.add(new Entidade("/agua.png","agua",false,1,3));
		entidades.add(new Entidade("/agua.png","agua",false,1,2));
		entidades.add(new Entidade("/agua.png","agua",false,1,1));
		entidades.add(new Entidade("/maca.png","maca",false,0,2));
		entidades.add(new Entidade("/personagem.png","personagem",true, 0, 0));
		
		
		final JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new GridLayout(tabuleiro.getTabuleiro().length, tabuleiro.getTabuleiro()[0].length));

		final Main2 main = new Main2();
		final String[][] posicoes = tabuleiro.getTabuleiro();
//		JOptionPane.showMessageDialog(frame, posicoes.length);
//		JOptionPane.showMessageDialog(frame, posicoes[0].length);
		for (int i = 0; i < posicoes.length; i++) {
			for (int j = 0; j < posicoes[0].length; j++) {
				frame.add(new JLabel(new Entidade("/grama.png","grama",false).getIcone()));
			}
		}
		for(Entidade entidadeArray : entidades)
		{
			if(!entidadeArray.getNome().equals("grama"))
			{
				((JLabel) frame.getContentPane().getComponent( entidadeArray.getPosicaoXNoMapa() * frame.getContentPane().WIDTH + entidadeArray.getPosicaoYNoMapa())).setIcon(entidadeArray.getIcone());
			}
			
		}	
		numeroDeMacas = numeroDeMacasTemp;

		frame.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
			}

			@Override
			public void keyPressed(KeyEvent e) {
				switch (e.getKeyCode()) {
				case 37:
					for (int i = 0; i < posicoes.length; i++) {
						for (int j = 0; j < posicoes[0].length ; j++) {
							if (posicoes[i][j].equals("personagem")) {
								if(j-1 > 0)
								{
									if (posicoes[i][j - 1].equals("agua")) {
										JOptionPane.showMessageDialog(frame, "Perdeu!", "Perdeu!", JOptionPane.ERROR_MESSAGE);
										System.exit(0);
									}
									if (posicoes[i][j - 1].equals("maca")) {
										++main.coletados;
										if (main.coletados == numeroDeMacas) {
											JOptionPane.showMessageDialog(frame, "Ganhou!", "Ganhou!", JOptionPane.INFORMATION_MESSAGE);
											System.exit(0);
										}
									}
									posicoes[i][j] = "grama";
									((JLabel) frame.getContentPane().getComponent(i * 15 + j)).setIcon(icone(3));
									posicoes[i][j - 1] = "personagem";
									((JLabel) frame.getContentPane().getComponent(i * 15 + j - 1)).setIcon(icone(2));
								}
							}
						}
					}
					break;
				case 38:
					for (int i = 0; i < posicoes.length; i++) {
						for (int j = 0; j < posicoes[0].length; j++) {
							if (posicoes[i][j].equals("personagem")) {
								if(j-1 > 0)
								{
									if (posicoes[i - 1][j].equals("agua")) {
										JOptionPane.showMessageDialog(frame, "Perdeu!", "Perdeu!", JOptionPane.ERROR_MESSAGE);
										System.exit(0);
									}
									if (posicoes[i - 1][j].equals("maca")) {
										++main.coletados;
										if (main.coletados == numeroDeMacas) {
											JOptionPane.showMessageDialog(frame, "Ganhou!", "Ganhou!", JOptionPane.INFORMATION_MESSAGE);
											System.exit(0);
										}
									}
									posicoes[i][j] = "grama";
									((JLabel) frame.getContentPane().getComponent(i * 15 + j)).setIcon(icone(3));
									posicoes[i - 1][j] = "personagem";
									((JLabel) frame.getContentPane().getComponent((i - 1) * 15 + j)).setIcon(icone(2));
								}
							}
						}
					}
					break;
				case 39:
					for (int i = 0; i < posicoes.length; i++) {
						for (int j = 0; j < posicoes[0].length; j++) {
							if (posicoes[i][j].equals("personagem")) {
								if(j+1 <= posicoes[0].length)
								{
									if (posicoes[i][j + 1].equals("agua")) {
										JOptionPane.showMessageDialog(frame, "Perdeu!", "Perdeu!", JOptionPane.ERROR_MESSAGE);
										System.exit(0);
									}
									if (posicoes[i][j + 1].equals("maca")) {
										++main.coletados;
										if (main.coletados == numeroDeMacas) {
											JOptionPane.showMessageDialog(frame, "Ganhou!", "Ganhou!", JOptionPane.INFORMATION_MESSAGE);
											System.exit(0);
										}
									}
									posicoes[i][j] = "grama";
									((JLabel) frame.getContentPane().getComponent(i * 15 + j)).setIcon(icone(3));
									posicoes[i][j + 1] = "personagem";
									((JLabel) frame.getContentPane().getComponent(i * 15 + j + 1)).setIcon(icone(2));
									break;
								}
							}
						}
					}
					break;
				case 40:
					break;
				}
			}

			private Icon icone(int i) {
				try {
					switch (i) {
					case 0: return new ImageIcon(ImageIO.read(Main2.class.getResourceAsStream("/agua.png")));
					case 1: return new ImageIcon(ImageIO.read(Main2.class.getResourceAsStream("/maca.png")));
					case 2: return new ImageIcon(ImageIO.read(Main2.class.getResourceAsStream("/personagem.png")));
					case 3: return new ImageIcon(ImageIO.read(Main2.class.getResourceAsStream("/grama.png")));
					default: throw new IllegalArgumentException("" + i);
					}
				} catch (Exception e) {
					throw new RuntimeException(e);
				}
			}
		});

		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

}
