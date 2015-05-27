package arquiteturadefinida.logicajogo;

import java.util.ArrayList;
import java.util.Random;

public class InteligenciaArtificial {
	
	Elemento inimigo;
	int altura, largura;
	Tabuleiro tabuleiro;
	
	public InteligenciaArtificial(Tabuleiro tabuleiro, Elemento inimigo) {
		altura = tabuleiro.getNumeroLinhas();
		largura = tabuleiro.getNumeroColunas();
		this.inimigo = inimigo;
		this.tabuleiro = tabuleiro;

	}
	
	public boolean existeObstaculoNaDirecao(Direcao direcao, Posicao posicao) {
		
		if(tabuleiro.posicaoEhInvalida(posicao.somar(direcao))) {
			if(tabuleiro.elementoEm(posicao.somar(direcao)).getEhObstaculo()) {
				return true;
			}
			else {
				return (existeObstaculoNaDirecao(direcao,posicao.somar(direcao)));
			}
			
		}
		else {
			return false;
		}
	}
	
	
	public void moverInimigo() {
		if(tabuleiro.acharPosicaoDe(inimigo)!= null) {
			Direcao direcao;
			direcao = acharCaminho(null, tabuleiro.acharPosicaoDe(inimigo));
			if(direcao != null) {
				tabuleiro.fazerMovimento(direcao, inimigo);
			}

		}
	}
	
	public Elemento acharJogadorMaisProximo(Posicao posicao) {
		Elemento jogador1 = null,jogador2 = null;
		
		try{
			
			for(int i =0; i < tabuleiro.getNumeroColunas() ; i++) {
				for(int j = 0; j < tabuleiro.getNumeroLinhas() ; j++) {
					if(tabuleiro.elementoEm(new Posicao(i,j)).ehJogador()) {
						if(jogador1==null) {
							jogador1 = tabuleiro.elementoEm(new Posicao(i,j));
						}
						else {
							jogador2 = tabuleiro.elementoEm(new Posicao(i,j));
							i = tabuleiro.getNumeroColunas();
							break;
						}
						
					}
				}
				
			}
			
			if( ( Math.abs(tabuleiro.acharPosicaoDe(jogador1).getColuna() - posicao.getColuna()) + Math.abs(tabuleiro.acharPosicaoDe(jogador1).getLinha() - posicao.getLinha()) )  >  ( Math.abs(tabuleiro.acharPosicaoDe(jogador2).getColuna() - posicao.getColuna()) + Math.abs(tabuleiro.acharPosicaoDe(jogador2).getLinha() - posicao.getLinha()) )     ) {
				return jogador2;
			}
			else if( ( Math.abs(tabuleiro.acharPosicaoDe(jogador1).getColuna() - posicao.getColuna()) + Math.abs(tabuleiro.acharPosicaoDe(jogador1).getLinha() - posicao.getLinha()) )  <  ( Math.abs(tabuleiro.acharPosicaoDe(jogador2).getColuna() - posicao.getColuna()) + Math.abs(tabuleiro.acharPosicaoDe(jogador2).getLinha() - posicao.getLinha()) )     ) {
				return jogador1;
			}
			else
			{
				if((new Random().nextInt() * 3 ) == 1 ) {
					return jogador1;
				}
				else {
					return jogador2;
				}
			}
		}
		catch(Exception exception){
			if(tabuleiro.acharPosicaoDe(Elemento.PERSONAGEM)== null){
				return Elemento.PERSONAGEM2;
			}
			else {
				return Elemento.PERSONAGEM;
			}
			
		}
		
	}
	
	private Direcao acharCaminho(Direcao direcaoInicial, Posicao posicao) {
		int diferencaDeLinhas, diferencaDeColunas;
		Elemento alvo = acharJogadorMaisProximo(posicao);
		try {
			try{
				diferencaDeLinhas = tabuleiro.acharPosicaoDe(alvo).getLinha() - posicao.getLinha();
				diferencaDeColunas = tabuleiro.acharPosicaoDe(alvo).getColuna() - posicao.getColuna();
			}
			catch(Exception exception){
				diferencaDeLinhas = diferencaDeColunas = 0;
			}
			if(tabuleiro.elementoEm(posicao.somar(Direcao.BAIXO)).equals(alvo)) {
				direcaoInicial = direcaoInicial == null ? Direcao.BAIXO : direcaoInicial;
				return direcaoInicial;
			}
			else if(tabuleiro.elementoEm(posicao.somar(Direcao.CIMA)).ehJogador()) {
				direcaoInicial = direcaoInicial == null ? Direcao.CIMA : direcaoInicial;
				return direcaoInicial;
			}
			else if(tabuleiro.elementoEm(posicao.somar(Direcao.DIREITA)).ehJogador()) {
				direcaoInicial = direcaoInicial == null ? Direcao.DIREITA : direcaoInicial;
				return direcaoInicial;
			}
			else if(tabuleiro.elementoEm(posicao.somar(Direcao.ESQUERDA)).ehJogador()) {
				direcaoInicial = direcaoInicial == null ? Direcao.ESQUERDA : direcaoInicial;
				return direcaoInicial;
			}
			
			if(
				diferencaDeLinhas==0 
				&&
			    (!tabuleiro.elementoEm(posicao.somar(Direcao.DIREITA)).getEhObstaculo() && !tabuleiro.posicaoEhInvalida(posicao.somar(Direcao.DIREITA))) 
			    && diferencaDeColunas>0	 && !existeObstaculoNaDirecao(Direcao.DIREITA,tabuleiro.acharPosicaoDe(inimigo))
			    ) {
					direcaoInicial = direcaoInicial == null ? Direcao.DIREITA : direcaoInicial;
					return acharCaminho(direcaoInicial, posicao.somar(direcaoInicial));
				}
			else if(
					diferencaDeLinhas==0 
					&&
				    (!tabuleiro.elementoEm(posicao.somar(Direcao.ESQUERDA)).getEhObstaculo() && !tabuleiro.posicaoEhInvalida(posicao.somar(Direcao.ESQUERDA))) 
				    && diferencaDeColunas<0	 && !existeObstaculoNaDirecao(Direcao.ESQUERDA,tabuleiro.acharPosicaoDe(inimigo))
				    ) {
					direcaoInicial = direcaoInicial == null ? Direcao.ESQUERDA : direcaoInicial;
					return acharCaminho(direcaoInicial, posicao.somar(direcaoInicial));
				}
			
		
			if(
					diferencaDeColunas==0 
					&&
				    (!tabuleiro.elementoEm(posicao.somar(Direcao.BAIXO)).getEhObstaculo() && !tabuleiro.posicaoEhInvalida(posicao.somar(Direcao.BAIXO))) 
				    && diferencaDeLinhas>0	&& !existeObstaculoNaDirecao(Direcao.BAIXO,tabuleiro.acharPosicaoDe(inimigo))
				    ) {
					direcaoInicial = direcaoInicial == null ? Direcao.BAIXO : direcaoInicial;
					return acharCaminho(direcaoInicial, posicao.somar(direcaoInicial));
				}
				else if (
						diferencaDeColunas==0 
						&&
					    (!tabuleiro.elementoEm(posicao.somar(Direcao.CIMA)).getEhObstaculo() && !tabuleiro.posicaoEhInvalida(posicao.somar(Direcao.CIMA))) 
					    && diferencaDeLinhas<0	&& !existeObstaculoNaDirecao(Direcao.CIMA,tabuleiro.acharPosicaoDe(inimigo))
					    ) {
					direcaoInicial = direcaoInicial == null ? Direcao.CIMA : direcaoInicial;
					return acharCaminho(direcaoInicial, posicao.somar(direcaoInicial));
				}
		
		//Caso o personagem não esteja na mesma linha que o inimigo ele tentará calcular a melhor rota
			if(
					Math.abs(diferencaDeLinhas)>Math.abs(diferencaDeColunas)
					&&
				    (!tabuleiro.elementoEm(posicao.somar(Direcao.DIREITA)).getEhObstaculo() && !tabuleiro.posicaoEhInvalida(posicao.somar(Direcao.DIREITA))) 
				    && diferencaDeColunas>0	
				    ) {
						direcaoInicial = direcaoInicial == null ? Direcao.DIREITA : direcaoInicial;
						return acharCaminho(direcaoInicial,posicao.somar(direcaoInicial));
					}
			else if(
					Math.abs(diferencaDeLinhas)>Math.abs(diferencaDeColunas)
					&&
				    (!tabuleiro.elementoEm(posicao.somar(Direcao.ESQUERDA)).getEhObstaculo() && !tabuleiro.posicaoEhInvalida(posicao.somar(Direcao.ESQUERDA))) 
				    && diferencaDeColunas<0	
				    ) {
						direcaoInicial = direcaoInicial == null ? Direcao.ESQUERDA : direcaoInicial;
						return acharCaminho(direcaoInicial,posicao.somar(direcaoInicial));
					}
			else if(
					Math.abs(diferencaDeLinhas)<Math.abs(diferencaDeColunas)
					&&
				    (!tabuleiro.elementoEm(posicao.somar(Direcao.CIMA)).getEhObstaculo() && !tabuleiro.posicaoEhInvalida(posicao.somar(Direcao.CIMA))) 
				    && diferencaDeLinhas<0	
				    ) {
						direcaoInicial = direcaoInicial == null ? Direcao.CIMA : direcaoInicial;
						return acharCaminho(direcaoInicial,posicao.somar(direcaoInicial));
					}
			else if(
					Math.abs(diferencaDeLinhas)<Math.abs(diferencaDeColunas)
					&&
				    (!tabuleiro.elementoEm(posicao.somar(Direcao.BAIXO)).getEhObstaculo() && !tabuleiro.posicaoEhInvalida(posicao.somar(Direcao.BAIXO))) 
				    && diferencaDeLinhas>0	
				    ) {
						direcaoInicial = direcaoInicial == null ? Direcao.BAIXO : direcaoInicial;
						return acharCaminho(direcaoInicial,posicao.somar(direcaoInicial));
					}
			else  if
				((!tabuleiro.elementoEm(posicao.somar(Direcao.BAIXO)).getEhObstaculo() && !tabuleiro.posicaoEhInvalida(posicao.somar(Direcao.BAIXO))) 
			    && diferencaDeLinhas>0	
			    ) {
					direcaoInicial = direcaoInicial == null ? Direcao.BAIXO : direcaoInicial;
					return acharCaminho(direcaoInicial,posicao.somar(direcaoInicial));
			}
		    else if ((!tabuleiro.elementoEm(posicao.somar(Direcao.CIMA)).getEhObstaculo() && !tabuleiro.posicaoEhInvalida(posicao.somar(Direcao.CIMA))) 
			    && diferencaDeLinhas<0	
			    ) {
					direcaoInicial = direcaoInicial == null ? Direcao.CIMA : direcaoInicial;
					return acharCaminho(direcaoInicial,posicao.somar(direcaoInicial));
				}
		    else if((!tabuleiro.elementoEm(posicao.somar(Direcao.ESQUERDA)).getEhObstaculo() && !tabuleiro.posicaoEhInvalida(posicao.somar(Direcao.ESQUERDA))) 
				    && diferencaDeColunas<0	
				    ) {
						direcaoInicial = direcaoInicial == null ? Direcao.ESQUERDA : direcaoInicial;
						return acharCaminho(direcaoInicial,posicao.somar(direcaoInicial));
					}
		    else if((!tabuleiro.elementoEm(posicao.somar(Direcao.DIREITA)).getEhObstaculo() && !tabuleiro.posicaoEhInvalida(posicao.somar(Direcao.DIREITA))) 
				    && diferencaDeColunas>0	
				    ) {
						direcaoInicial = direcaoInicial == null ? Direcao.DIREITA : direcaoInicial;
						return acharCaminho(direcaoInicial,posicao.somar(direcaoInicial));
					}
		
			
		
		return direcaoInicial;
		}
		catch(Exception exception) {
			return direcaoInicial;
		}
	}
}