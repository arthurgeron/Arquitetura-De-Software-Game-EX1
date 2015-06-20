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
	
	
		private boolean existeObstaculoNaDirecao(Direcao_Inimigo direcao, Posicao posicaoAtual, int limiteDePassos) {

		if(tabuleiro.posicaoEhInvalida(posicaoAtual.somar(direcao)))
		{
			if(limiteDePassos  >= 0){
				if(tabuleiro.elementoEm(posicaoAtual.somar(direcao)) instanceof ElementoEstatico) {
					return true;
				}
				else {
					return (existeObstaculoNaDirecao(direcao,posicaoAtual.somar(direcao), limiteDePassos--));
				}
			}
		}//É necessário verificar se a próxima posição realmente não é um obstáculo
		else if(tabuleiro.elementoEm(posicaoAtual.somar(direcao))instanceof ElementoEstatico)
			return true;
		return false;
		
		}
	
	private boolean existeAlvoNaDirecao(Direcao_Inimigo direcao, Posicao posicaoAtual, Elemento alvo) {
		
		if(!posicaoNaDirecaoEhValida(posicaoAtual,direcao)) {
			return false;
		}
		else if(tabuleiro.elementoEm(posicaoAtual.somar(direcao))instanceof ElementoEstatico){
			return false;
		}
		else if(tabuleiro.elementoEm(posicaoAtual.somar(direcao)).equals(alvo)) {
			return true;
		}
		else if(!(tabuleiro.elementoEm(posicaoAtual.somar(direcao))instanceof ElementoEstatico)){
			return existeAlvoNaDirecao(direcao,posicaoAtual.somar(direcao),alvo);
		}
		return false;
}
	
	private Direcao_Inimigo escolherDirecaoAleatoria(Posicao posicao) {
		Random randomico = new Random();
		Direcao_Inimigo direcaoEscolhida = null;
		int contadorDePassos = 0;
		while(contadorDePassos<5) {
			switch(randomico.nextInt(4)+1) {
				case 1:
					direcaoEscolhida = Direcao_Inimigo.ESQUERDA;
					break;
					
				case 2:
					direcaoEscolhida = Direcao_Inimigo.DIREITA;
					break;
					
				case 3:
					direcaoEscolhida = Direcao_Inimigo.CIMA;
					break;
					
				case 4:
					direcaoEscolhida = Direcao_Inimigo.BAIXO;
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
			Direcao_Inimigo direcao;
			direcao = acharCaminho(tabuleiro.acharPosicaoDe(inimigo));
			if(direcao != null) {
				tabuleiro.fazerMovimentoInimigo(direcao, inimigo);
			}

		}
	}
	
	private boolean posicaoNaDirecaoEhValida(Posicao posicao, Direcao_Inimigo direcao) {
		if(!tabuleiro.posicaoEhInvalida(posicao) && !tabuleiro.posicaoEhInvalida(posicao.somar(direcao))){
			if(!(tabuleiro.elementoEm(posicao.somar(direcao))instanceof ElementoEstatico)) {
				return true;
			}
		}
		return false;
	}
	
	private Elemento acharJogadorMaisProximo(Posicao posicao) {
		 Elemento alvo = null;
		 int diferencaDeColunas = 0 , diferencaDeLinhas = 0;
	 
		for(int i =0; i < tabuleiro.getNumeroColunas() ; i++) {
			for(int j = 0; j < tabuleiro.getNumeroLinhas() ; j++) {
				if(tabuleiro.elementoEm(new Posicao(i,j)) instanceof Personagem) {
					if(alvo==null) {
						alvo = tabuleiro.elementoEm(new Posicao(i,j));
						diferencaDeLinhas = Math.abs(tabuleiro.acharPosicaoDe(alvo).getLinha() - posicao.getLinha());
						diferencaDeColunas = Math.abs(tabuleiro.acharPosicaoDe(alvo).getColuna() - posicao.getColuna());
					}
					else {
						int diferencaDeColunasNovoAlvo, diferencaDeLinhasNovoAlvo;
						diferencaDeLinhasNovoAlvo = Math.abs(tabuleiro.acharPosicaoDe(alvo).getLinha() - posicao.getLinha());
						diferencaDeColunasNovoAlvo = Math.abs(tabuleiro.acharPosicaoDe(alvo).getColuna() - posicao.getColuna());
						
						if((diferencaDeLinhasNovoAlvo + diferencaDeColunasNovoAlvo) > (diferencaDeLinhas + diferencaDeColunas)){
							alvo = tabuleiro.elementoEm(new Posicao(i,j));
							diferencaDeLinhas = diferencaDeLinhasNovoAlvo;
							diferencaDeColunas = diferencaDeColunasNovoAlvo;
						}
					
					}
					
				}
			}
			
		}
		
		return alvo;
		
	}
	
	private Direcao_Inimigo acharCaminho(Posicao posicao) {
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
			
			for(Direcao_Inimigo direcao : Direcao_Inimigo.values()){
				if(existeAlvoNaDirecao(direcao,posicao,alvo)){
					return direcao;
				}
			}	
			
			if(Math.abs(diferencaDeLinhas)>Math.abs(diferencaDeColunas)) {
				if(diferencaDeLinhas>0) {
					if(!existeObstaculoNaDirecao(Direcao_Inimigo.BAIXO,posicao,diferencaDeLinhas)) {
							return Direcao_Inimigo.BAIXO;
					}
				}
				else if(!existeObstaculoNaDirecao(Direcao_Inimigo.CIMA,posicao,Math.abs(diferencaDeLinhas))) {
					return Direcao_Inimigo.CIMA;
				}
				
				if(diferencaDeColunas>0) {
					if(!existeObstaculoNaDirecao(Direcao_Inimigo.DIREITA,posicao,diferencaDeColunas)){
							return Direcao_Inimigo.DIREITA;
					}
				}
				else if(!existeObstaculoNaDirecao(Direcao_Inimigo.ESQUERDA,posicao,Math.abs(diferencaDeColunas))) {
							return Direcao_Inimigo.ESQUERDA;
				}				
			}
			else {
				if(diferencaDeColunas>0) {
					if(!existeObstaculoNaDirecao(Direcao_Inimigo.DIREITA,posicao,diferencaDeColunas)) {
							return Direcao_Inimigo.DIREITA;
					}
				}
				else if(!existeObstaculoNaDirecao(Direcao_Inimigo.ESQUERDA,posicao,Math.abs(diferencaDeColunas))) {
					return Direcao_Inimigo.ESQUERDA;
				}
				
				if(diferencaDeLinhas>0) {
					if(!existeObstaculoNaDirecao(Direcao_Inimigo.BAIXO,posicao,diferencaDeLinhas)){
							return Direcao_Inimigo.BAIXO;
					}
				}
				else if(!existeObstaculoNaDirecao(Direcao_Inimigo.CIMA,posicao,Math.abs(diferencaDeLinhas))) {
							return Direcao_Inimigo.CIMA;
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