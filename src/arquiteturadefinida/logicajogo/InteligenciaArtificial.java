package arquiteturadefinida.logicajogo;

import java.util.ArrayList;

public class InteligenciaArtificial {
	
	Elemento inimigo;
	int altura, largura;
	Tabuleiro tabuleiro;
	ArrayList<int[]> posicoesAndadasOuInvalidas = new ArrayList<int[]>();
	
	public InteligenciaArtificial(Tabuleiro tabuleiro, Elemento inimigo) {
		altura = tabuleiro.getNumeroLinhas();
		largura = tabuleiro.getNumeroColunas();
		this.inimigo = inimigo;
		this.tabuleiro = tabuleiro;

		for(int i = 0 ; i < altura ; i++) {
			for(int j = 0 ; j < largura ; j++) {
				if(tabuleiro.elementoEm(new Posicao(i,j)).getEhObstaculo()) {
					posicoesAndadasOuInvalidas.add(new int[]{i,j});
				}
			}
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
	private Direcao acharCaminho(Direcao direcaoInicial, Posicao posicao) {
		int diferencaDeLinhas, diferencaDeColunas;
		diferencaDeLinhas = tabuleiro.acharPosicaoDe(Elemento.PERSONAGEM).getLinha() - posicao.getLinha();
		diferencaDeColunas = tabuleiro.acharPosicaoDe(Elemento.PERSONAGEM).getColuna() - posicao.getColuna();
		try {
			if(tabuleiro.elementoEm(posicao.somar(Direcao.BAIXO)).equals(Elemento.PERSONAGEM)) {
				direcaoInicial = direcaoInicial == null ? Direcao.BAIXO : direcaoInicial;
				return direcaoInicial;
			}
			else if(tabuleiro.elementoEm(posicao.somar(Direcao.CIMA)).equals(Elemento.PERSONAGEM)) {
				direcaoInicial = direcaoInicial == null ? Direcao.CIMA : direcaoInicial;
				return direcaoInicial;
			}
			else if(tabuleiro.elementoEm(posicao.somar(Direcao.DIREITA)).equals(Elemento.PERSONAGEM)) {
				direcaoInicial = direcaoInicial == null ? Direcao.DIREITA : direcaoInicial;
				return direcaoInicial;
			}
			else if(tabuleiro.elementoEm(posicao.somar(Direcao.ESQUERDA)).equals(Elemento.PERSONAGEM)) {
				direcaoInicial = direcaoInicial == null ? Direcao.ESQUERDA : direcaoInicial;
				return direcaoInicial;
			}
			
			if(
				diferencaDeLinhas==0 
				&&
			    (!posicoesAndadasOuInvalidas.contains(new int[]{posicao.somar(Direcao.DIREITA).getLinha(),posicao.getColuna()}) && !tabuleiro.posicaoEhInvalida(posicao.somar(Direcao.DIREITA))) 
			    && diferencaDeColunas>0	
			    ) {
					direcaoInicial = direcaoInicial == null ? Direcao.DIREITA : direcaoInicial;
					return acharCaminho(direcaoInicial, posicao.somar(direcaoInicial));
				}
			else if(
					diferencaDeLinhas==0 
					&&
				    (!posicoesAndadasOuInvalidas.contains(new int[]{posicao.somar(Direcao.ESQUERDA).getLinha(),posicao.getColuna()}) && !tabuleiro.posicaoEhInvalida(posicao.somar(Direcao.ESQUERDA))) 
				    && diferencaDeColunas<0	
				    ) {
					direcaoInicial = direcaoInicial == null ? Direcao.ESQUERDA : direcaoInicial;
					return acharCaminho(direcaoInicial, posicao.somar(direcaoInicial));
				}
			
		
			if(
					diferencaDeColunas==0 
					&&
				    (!posicoesAndadasOuInvalidas.contains(new int[]{posicao.somar(Direcao.BAIXO).getLinha(),posicao.getColuna()}) && !tabuleiro.posicaoEhInvalida(posicao.somar(Direcao.BAIXO))) 
				    && diferencaDeLinhas>0	
				    ) {
					direcaoInicial = direcaoInicial == null ? Direcao.BAIXO : direcaoInicial;
					return acharCaminho(direcaoInicial, posicao.somar(direcaoInicial));
				}
				else if (
						diferencaDeColunas==0 
						&&
					    (!posicoesAndadasOuInvalidas.contains(new int[]{posicao.somar(Direcao.CIMA).getLinha(),posicao.getColuna()}) && !tabuleiro.posicaoEhInvalida(posicao.somar(Direcao.CIMA))) 
					    && diferencaDeLinhas<0	
					    ) {
					direcaoInicial = direcaoInicial == null ? Direcao.CIMA : direcaoInicial;
					return acharCaminho(direcaoInicial, posicao.somar(direcaoInicial));
				}
		
		//Caso o personagem não esteja na mesma linha que o inimigo ele tentará calcular a melhor rota
			if(
					Math.abs(diferencaDeLinhas)>=Math.abs(diferencaDeColunas)
					&&
				    (!posicoesAndadasOuInvalidas.contains(new int[]{posicao.somar(Direcao.DIREITA).getLinha(),posicao.getColuna()}) && !tabuleiro.posicaoEhInvalida(posicao.somar(Direcao.DIREITA))) 
				    && diferencaDeColunas>0	
				    ) {
//					if(direcaoInicial == null){
//						direcaoInicial = Direcao.DIREITA;
//					}
						direcaoInicial = direcaoInicial == null ? Direcao.DIREITA : direcaoInicial;
						return acharCaminho(direcaoInicial,posicao.somar(direcaoInicial));
					}
			if(
					Math.abs(diferencaDeLinhas)>=Math.abs(diferencaDeColunas)
					&&
				    (!posicoesAndadasOuInvalidas.contains(new int[]{posicao.somar(Direcao.ESQUERDA).getLinha(),posicao.getColuna()}) && !tabuleiro.posicaoEhInvalida(posicao.somar(Direcao.ESQUERDA))) 
				    && diferencaDeColunas<0	
				    ) {
						direcaoInicial = direcaoInicial == null ? Direcao.ESQUERDA : direcaoInicial;
						return acharCaminho(direcaoInicial,posicao.somar(direcaoInicial));
					}
			if(
					Math.abs(diferencaDeLinhas)<Math.abs(diferencaDeColunas)
					&&
				    (!posicoesAndadasOuInvalidas.contains(new int[]{posicao.somar(Direcao.CIMA).getLinha(),posicao.getColuna()}) && !tabuleiro.posicaoEhInvalida(posicao.somar(Direcao.CIMA))) 
				    && diferencaDeLinhas<0	
				    ) {
						direcaoInicial = direcaoInicial == null ? Direcao.CIMA : direcaoInicial;
						return acharCaminho(direcaoInicial,posicao.somar(direcaoInicial));
					}
			if(
					Math.abs(diferencaDeLinhas)<Math.abs(diferencaDeColunas)
					&&
				    (!posicoesAndadasOuInvalidas.contains(new int[]{posicao.somar(Direcao.BAIXO).getLinha(),posicao.getColuna()}) && !tabuleiro.posicaoEhInvalida(posicao.somar(Direcao.BAIXO))) 
				    && diferencaDeLinhas>0	
				    ) {
						direcaoInicial = direcaoInicial == null ? Direcao.BAIXO : direcaoInicial;
						return acharCaminho(direcaoInicial,posicao.somar(direcaoInicial));
					}
		
			
		
		return direcaoInicial;
		}
		catch(Exception exception) {
			System.out.println(exception);
			return direcaoInicial;
		}
	}
}