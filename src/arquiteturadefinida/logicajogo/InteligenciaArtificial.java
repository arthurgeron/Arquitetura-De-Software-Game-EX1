package arquiteturadefinida.logicajogo;


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
	
	
		private boolean existeObstaculoNaDirecao(Direcao direcao, Posicao posicaoAtual, int limiteDePassos) {
		
			if(tabuleiro.posicaoEhInvalida(posicaoAtual.somar(direcao)) && limiteDePassos  > 0) {
				if(tabuleiro.elementoEm(posicaoAtual.somar(direcao)).getEhObstaculo()) {
					return true;
				}
				else {
					return (existeObstaculoNaDirecao(direcao,posicaoAtual.somar(direcao), limiteDePassos--));
				}
				
			}
			return false;
		}
		
	
	private boolean existePersonagemNaDirecao(Direcao direcao, Posicao posicaoAtual) {
			
			if(tabuleiro.posicaoEhInvalida(posicaoAtual)) {
				return false;
			}
			if(!tabuleiro.posicaoEhInvalida(posicaoAtual.somar(direcao))) {
				if(tabuleiro.elementoEm(posicaoAtual.somar(direcao)).ehJogador()) {
					return true;
				}
				else if(!tabuleiro.elementoEm(posicaoAtual.somar(direcao)).getEhObstaculo()){
					return existePersonagemNaDirecao(direcao,posicaoAtual.somar(direcao));
				}
				
			}
			return false;
	}
	private boolean existeAlvoNaDirecao(Direcao direcao, Posicao posicaoAtual, Elemento alvo) {
		
		if(!posicaoNaDirecaoEhValida(posicaoAtual,direcao)) {
			return false;
		}
		else if(tabuleiro.elementoEm(posicaoAtual.somar(direcao)).getEhObstaculo()){
			return false;
		}
		else if(tabuleiro.elementoEm(posicaoAtual.somar(direcao)).equals(alvo)) {
			return true;
		}
		else if(!tabuleiro.elementoEm(posicaoAtual.somar(direcao)).getEhObstaculo()){
			return existeAlvoNaDirecao(direcao,posicaoAtual.somar(direcao),alvo);
		}
		return false;
}
	
	public Direcao escolherDirecaoAleatoria(Posicao posicao) {
		Random randomico = new Random();
		Direcao direcaoEscolhida = null;
		int contadorDePassos = 0;
		while(contadorDePassos<5) {
			switch(randomico.nextInt(4)+1) {
				case 1:
					direcaoEscolhida = Direcao.ESQUERDA;
					break;
					
				case 2:
					direcaoEscolhida = Direcao.DIREITA;
					break;
					
				case 3:
					direcaoEscolhida = Direcao.CIMA;
					break;
					
				case 4:
					direcaoEscolhida = Direcao.BAIXO;
					break;
			
			}
			if( posicaoNaDirecaoEhValida(posicao,direcaoEscolhida)){
				contadorDePassos++;
			}
			else {
				break;
			}
		}
		return direcaoEscolhida;
		
	}
	public void moverInimigo() {
		if(tabuleiro.acharPosicaoDe(inimigo)!= null) {
			Direcao direcao;
			direcao = acharCaminho(tabuleiro.acharPosicaoDe(inimigo));
			if(direcao != null) {
				tabuleiro.fazerMovimento(direcao, inimigo);
			}

		}
	}
	
	private boolean posicaoNaDirecaoEhValida(Posicao posicao, Direcao direcao) {
		if(!tabuleiro.posicaoEhInvalida(posicao) && !tabuleiro.posicaoEhInvalida(posicao.somar(direcao))){
			if(!tabuleiro.elementoEm(posicao.somar(direcao)).getEhObstaculo()) {
				return true;
			}
		}
		return false;
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
	
	private Direcao acharCaminho(Posicao posicao) {
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
			if(existeAlvoNaDirecao(Direcao.BAIXO,posicao,alvo)) {
				return Direcao.BAIXO;
			}
			else if(existeAlvoNaDirecao(Direcao.CIMA,posicao,alvo)) {
				return Direcao.CIMA;
			}
			else if(existeAlvoNaDirecao(Direcao.DIREITA,posicao,alvo)) {
				return Direcao.DIREITA;
			}
			else if(existeAlvoNaDirecao(Direcao.ESQUERDA,posicao,alvo)) {
				return Direcao.ESQUERDA;
			}
		
			
			
			
			//Caso o personagem não esteja na mesma linha ou existam obstáculos 
			
			
			if(Math.abs(diferencaDeLinhas)>Math.abs(diferencaDeColunas)) {
				if(diferencaDeLinhas>0) {
					if(!existeObstaculoNaDirecao(Direcao.BAIXO,posicao,diferencaDeLinhas)) {
							return Direcao.BAIXO;
					}
				}
				else if(!existeObstaculoNaDirecao(Direcao.CIMA,posicao,Math.abs(diferencaDeLinhas))) {
					return Direcao.CIMA;
				}
				
				if(diferencaDeColunas>0) {
					if(!existeObstaculoNaDirecao(Direcao.DIREITA,posicao,diferencaDeColunas)){
							return Direcao.DIREITA;
					}
				}
				else if(!existeObstaculoNaDirecao(Direcao.ESQUERDA,posicao,Math.abs(diferencaDeColunas))) {
							return Direcao.ESQUERDA;
				}
				
				
			}
			else {
				if(diferencaDeColunas>0) {
					if(!existeObstaculoNaDirecao(Direcao.DIREITA,posicao,diferencaDeColunas)) {
							return Direcao.DIREITA;
					}
				}
				else if(!existeObstaculoNaDirecao(Direcao.ESQUERDA,posicao,Math.abs(diferencaDeColunas))) {
					return Direcao.ESQUERDA;
				}
				
				if(diferencaDeLinhas>0) {
					if(!existeObstaculoNaDirecao(Direcao.BAIXO,posicao,diferencaDeLinhas)){
							return Direcao.BAIXO;
					}
				}
				else if(!existeObstaculoNaDirecao(Direcao.CIMA,posicao,Math.abs(diferencaDeLinhas))) {
							return Direcao.CIMA;
				}
			}
			

		
			return escolherDirecaoAleatoria(posicao);
		}
		catch(Exception exception) {
			System.out.println("1:"+exception.getStackTrace()[0].getLineNumber()+"\n2:"+exception.getStackTrace()[1].getLineNumber()+"\n3:"+exception.getStackTrace()[2].getLineNumber()+"\n4:"+exception.getStackTrace()[3].getLineNumber());
			return escolherDirecaoAleatoria(posicao);
		}
	}
}